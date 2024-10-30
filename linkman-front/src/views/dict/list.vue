<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.hosname" clearable placeholder="报修物品" />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
      <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
    </el-form>
    <!-- 列表 -->
    <el-table v-loading="listLoading" :data="list" stripe style="width:100%">
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="baoXiuId" label="报修物品" />
      <el-table-column prop="info" label="维修方式" />
      <el-table-column prop="niuMa" label="维修人员" />
      <el-table-column prop="state" label="状态" />
      <el-table-column prop="money" label="维修价格" />
      <el-table-column label="操作" width="400" align="center">
        <template slot-scope="scope">
          <el-button type="success" plain icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="success" v-if="scope.row.state == '未完成'" plain size="mini"
            @click="wancheng(scope.row.id)">完成</el-button>


          <el-button type="warning" plain size="mini" @click="lock(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>
    <!-- 分页组件 -->
    <el-pagination :current-page="page" :total="total" :page-size="limit" :page-sizes="[10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center;" layout="sizes, prev, pager, next, jumper, ->, total, slot"
      @current-change="fetchData" @size-change="changeSize" />

    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" v-model="form" label-width="80px">
        <el-row>
          <el-col :span="20">

            <el-form-item label="报修物品" prop="name">
              <el-input v-model="form.baoXiuId" placeholder="请输入报修物品" maxlength="50" />
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="20">
            <el-form-item label="维修方式" prop="info">
              <el-input v-model="form.info" placeholder="请输入维修方式" maxlength="50" />
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />
            </el-form-item>
          </el-col> -->
        </el-row>
        <el-row>
          <el-col :span="20">
            <el-form-item label="维修人员" prop="niuMa">
              <el-input v-model="form.niuMa" placeholder="请输入维修人员" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="20">
            <el-form-item label="维修价格" prop="money">
              <el-input v-model="form.money" type="number" min=0 placeholder="请输入维修价格" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import dictApi from '@/api/dict'

export default {
  data() {
    return {
      listLoading: true, // 数据是否正在加载
      list: null, // banner列表
      total: 0, // 数据库中的总记录数
      page: 1, // 默认页码
      limit: 10, // 每页记录数
      searchObj: {},
      form: {},
      open: false,
      title: "",
      form: {
        id: null, // 用户id
        baoXiuId: "", // 用户昵称
        info: "", // 用户手机号
        niuMa: "",
        state: "",
        money: 0, // 用户密码
      }, // 查询表单对象
    }
  },
  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {
    this.fetchData()
  },
  methods: {
    reset() {
      this.form = {
        id: null, // 用户id
        baoXiuId: "", // 用户昵称
        info: "", // 用户手机号
        niuMa: "",
        state: "",
        money: 0, // 用户密码
      }// 用户密码
    },
    // 当页码发生改变的时候
    changeSize(size) {
      console.log(size)
      this.limit = size
      this.fetchData(1)
    },
    // 加载banner列表数据
    fetchData(page = 1) {
      // 异步获取远程数据（ajax）
      this.page = page
      dictApi.getPageList(this.page, this.limit, this.searchObj).then(
        response => {
          this.list = response.data.records
          this.total = response.data.total
          // 数据加载并绑定成功
          this.listLoading = false
        }
      )
    },
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加维修";
    },
    handleUpdate(row) {
      this.form = row;
      this.open = true;
      this.title = "修改维修";

    },
    cancel() {
      this.open = false;
      this.reset();

    },
    /** 提交按钮 */
    submitForm() {
      dictApi.addUser(this.form).then(response => {
        this.open = false;
        if (this.form.id) {
          this.$message({
            type: 'success',
            message: `修改成功`
          });
        } else {
          this.$message({
            type: 'success',
            message: `添加成功`
          });
        }
        this.fetchData();
      }).catch(error => {
      });

    },

    // 重置查询表单
    resetData() {
      console.log('重置查询表单')
      this.searchObj = {}
      this.fetchData()
    },
    wancheng(id) {
      dictApi.wancheng(id).then(response => {
        this.fetchData(this.page)
        if (response.code) {
          this.$message({
            type: 'success',
            message: '操作成功!'
          })
        }
      })
    },
    // 锁定
    lock(id) {
      this.$confirm('确定该操作吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return dictApi.lock(id)
      }).then((response) => {
        this.fetchData(this.page)
        if (response.code) {
          this.$message({
            type: 'success',
            message: '操作成功!'
          })
        }
      })
    }
  }
}
</script>
