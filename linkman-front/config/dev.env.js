"use strict";
const merge = require("webpack-merge");
const prodEnv = require("./prod.env");

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://localhost:8081"'
  // BASE_API: '"http://easy-mock.com/mock/127.0.0.1/vue-admin"',
});
