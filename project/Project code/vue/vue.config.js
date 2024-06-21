const { defineConfig } = require('@vue/cli-service')
//引入jquery文件

module.exports = defineConfig({
  transpileDependencies: true,
  // devServer: {
  //   proxy: {
  //     '/api': {
  //       target: 'http://localhost:9090', // 这里是你的后端地址
  //       changeOrigin: true
  //     }
  //   }
  // }
})
