
<template>
  <div class="personal-center">
    <el-container style="height: 600px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: white">
        <el-menu :default-openeds="['1']">

          <el-submenu index="1" >
            <template slot="title">
              <i class="el-icon-postcard"></i>信息修改</template>
            <el-menu-item-group >

              <router-link class="alink" to="/BaseInfo">
                <el-menu-item index="1-1">
                  基本信息
                </el-menu-item>
              </router-link>
              <router-link class="alink" to="/ChangeInfo">
                <el-menu-item index="1-1">
                  修改或注销信息
                </el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>


          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-postcard"></i>文章管理</template>
            <el-menu-item-group>

              <router-link class="alink" to="/myspace">
                <el-menu-item index="2-3">
                  全部文章
                </el-menu-item>
              </router-link>

              <router-link class="alink" to="/myspace/columnArticle">
                <el-menu-item index="2-4">
                  我的专栏
                </el-menu-item>
              </router-link>

              <router-link class="alink" to="/myspace/statusArticle">
                <el-menu-item index="2-5">
                  审核状态
                </el-menu-item>
              </router-link>
            </el-menu-item-group>

          </el-submenu>


          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-edit"></i>文章书写
            </template>
            <el-menu-item-group>
              <router-link class="alink" to="/writeblog">
                <el-menu-item index="3-1">
                  文章书写
                </el-menu-item>
              </router-link>
            </el-menu-item-group>

          </el-submenu>

          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-postcard"></i>社区</template>

            <el-menu-item-group>

              <router-link class="alink" to="/myspace/myjoincolums">
                <el-menu-item index="4-1">
                  我的社区
                </el-menu-item>
              </router-link>

              <router-link class="alink" to="/myspace/mycolums">
                <el-menu-item index="4-2">
                  我创建的社区
                </el-menu-item>
              </router-link>


            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="5">
            <template slot="title">
              <i class="el-icon-star-on"></i>收藏</template>

            <el-menu-item-group>

              <router-link class="alink" to="/myspace/collectionArticle">
                <el-menu-item index="5-2">
                  文章收藏
                </el-menu-item>
              </router-link>

              <router-link class="alink" to="/myspace/collectionColums">
                <el-menu-item index="5-3">
                  社区收藏
                </el-menu-item>
              </router-link>

            </el-menu-item-group>


          </el-submenu>
        </el-menu>
      </el-aside>


      <el-container
          style="background-image: url('https://wallhaven.cc/w/rrpp6m');
        background-repeat: no-repeat;
        background-size:100% 100%;
        "
      >
        <el-main>
          <div style="width: 80%;margin: 0 auto">
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>

    </el-container>

  </div>
</template>

<script>
export default {
  name: "myspace",
  data() {

    return {

    }
  },
}
</script>

<style scoped>
.personal-center {
  background-color: rgba(255, 255, 255, 0.1); /* 设置透明背景颜色 */
  /* 或者使用背景图片 */
  /* background-image: url('透明背景图片路径'); */
  /* background-size: cover; */
  /* background-repeat: no-repeat; */
  /* background-position: center center; */
  padding: 10px;
  margin: 5px auto; /* 设置上下边距为 50px，水平居中 */
  width: 100%; /* 设置元素宽度为页面的 80% */
  position: relative; /* 设置相对定位，以便使用 top、right、bottom、left 控制位置 */
  top: 20px; /* 设置元素相对于其正常位置向下偏移 20px */
}
.el-header {
  background-color: #e5efe2;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
.router-link-active {
  text-decoration: none;
}

.alink{

  text-decoration: none;
}


</style>
