<template>
  <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <!-- <el-form-item>
        <el-input v-model="searchObj.keyword" placeholder="姓名/手机" />
      </el-form-item> -->



      <!-- <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button> -->
      <!-- <el-button type="default" @click="resetData()">清空</el-button> -->
      <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>

    </el-form>

    <!-- 列表 -->
    <el-table v-loading="listLoading" :data="list" stripe style="width: 100%">

      <el-table-column label="序号" width="170" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="phone" label="手机号" />
      <el-table-column prop="name" label="姓名" />


      <el-table-column label="操作" width="400" align="center">
        <template slot-scope="scope">
          <el-button type="success" plain icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">修改</el-button>

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
      <el-form ref="form" v-model="form" label-width="100px">
        <el-row>
          <el-col :span="20">
            <el-form-item label="联系人姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入联系人姓名" maxlength="30" />
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="20">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码" maxlength="11" />
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />
            </el-form-item>
          </el-col> -->
        </el-row>
        <!-- <el-row>
          <el-col :span="20">
            <el-form-item label="用户密码" prop="password">
              <el-input v-model="form.password" placeholder="请输入用户密码" type="password" maxlength="20" show-password />
            </el-form-item>
          </el-col>
        </el-row> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import userInfoApi from '@/api/userInfo'
export default {
  // 定义数据
  data() {
    return {
      listLoading: true, // 数据是否正在加载
      list: null, // banner列表
      total: 0, // 数据库中的总记录数
      page: 1, // 默认页码
      limit: 10, // 每页记录数
      searchObj: {},
      form: {
        id: null, // 用户id
        name: "", // 用户昵称
        phone: "", // 用户手机号
        password: "", // 用户密码
      }, // 查询表单对象
      title: "", // 对话框标题
      open: false,
      single: false, // 是否单选
    }
  },
  // 当页面加载时获取数据
  created() {
    this.fetchData()
  },
  methods: {
    reset() {
      this.form = {
        id: null, // 用户id
        name: "", // 用户昵称
        phone: "", // 用户手机号
        password: "",
      };
    },
    // 调用api层获取数据库中的数据
    fetchData(page = 1) {
      console.log(this.total)
      // 异步获取远程数据（ajax）
      this.page = page
      userInfoApi.getPageList(this.page, this.limit, this.searchObj).then(
        response => {
          this.list = response.data.records
          this.total = response.data.total
          // 数据加载并绑定成功
          this.listLoading = false
        }
      )
    },
    cancel() {
      this.open = false;
      this.reset();

    },
    // 当页码发生改变的时候
    changeSize(size) {
      this.limit = size
      this.fetchData(1)
    },
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户";
    },

    handleUpdate(row) {
      this.form = row;
      this.open = true;
      this.title = "修改用户";

    },
    /** 提交按钮 */
    submitForm() {
      userInfoApi.addUser(this.form).then(response => {
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
    // 锁定
    lock(id) {
      this.$confirm('确定该操作吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return userInfoApi.lock(id)
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
