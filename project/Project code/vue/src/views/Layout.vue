<template>
  <div>
    <!--  定义一个后端架构  -->
    <!--  头部区域  -->
    <div style="height: 60px;line-height: 60px;background-color: darkseagreen; margin-bottom: 2px; display: flex" >
      <div style="width: 300px">
        <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px;left:20px">
        <!-- 因为logo图像的relative相对位置脱离了文档流，所以对文字来说是无效的-->
        <span style="margin-left: 25px; font-size: 24px ">乳腺癌初步检测系统</span>
      </div>
      <div style="flex: 1; text-align: right; padding-right: 20px"><!-- 除了300，之外都属于他{{ user.name }}-->
        <el-dropdown size="medium">
          <span class="el-dropdown-link" style="cursor: pointer">
            {{ admin.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown" style="margin-top: -5px">
            <el-dropdown-item><div style="width: 50px; text-align: center;" @click="logout">退出</div></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <!--侧边栏和主体-->
    <div style="display: flex">
      <!--侧边栏导航default-openeds = "['/']默认打开位置-->
      <div style="width: 200px;min-height: calc(100vh - 62px); overflow: hidden;margin-right: 2px; background-color: darkseagreen">
        <div id="login"></div>
        <el-menu :default-active="$route.path" router class="el-menu-demo" >
          <el-menu-item index="/">
            <i class="el-icon-eleme"></i>
            <span>首页</span>
          </el-menu-item>
          <el-submenu index = "user">
            <template slot="title">
              <i class="el-icon-star-off"></i>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/addUser">用户添加</el-menu-item>
            <el-menu-item index="/userList">用户列表</el-menu-item>
          </el-submenu>
          <el-submenu index = "admin">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>管理员管理</span>
            </template>
            <el-menu-item index="/addAdmin">管理员添加</el-menu-item>
            <el-menu-item index="/adminList">管理员列表</el-menu-item>
          </el-submenu>
          <el-submenu index = "knowledge">
            <template slot="title">
              <i class="el-icon-s-home"></i>
              <span>抗癌社区</span>
            </template>
            <el-menu-item index="/addKnowledge">发布</el-menu-item>
            <el-menu-item index="/knowledgeList">文章列表</el-menu-item>
          </el-submenu>
          <el-submenu index = "Prediction">
            <template slot="title">
              <i class="el-icon-question"></i>
              <span>模型概要</span>
            </template>
            <el-menu-item index="/addPrediction">添加模型</el-menu-item>
            <el-menu-item index="/PredictionList">模型列表</el-menu-item>
            <el-menu-item index="/PredictionTest">测试模型</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>

      <!--主体数据-->
      <div style="flex: 1;background-color: white;padding: 10px">
      <!--用来展示子路由-->
        <router-view/>
        <!--  用于渲染路由  -->
      </div>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie';
export default {
  name: "Layout.vue",
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
    }
  },
  methods: {
    logout() {
      //清楚浏览器用户数据
      Cookies.remove('admin')
      this.$router.push('/ruleChange')

    }
  }
}
</script>

<style scoped>
.el-menu-demo{
  background-color: darkseagreen;
}
</style>