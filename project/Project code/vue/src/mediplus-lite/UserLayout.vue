<template>

  <div style="background-color: white">

    <header class="header" >
      <!-- Topbar -->
      <div class="topbar">
        <div class="container">
          <div class="row">
            <div class="col-lg-6 col-md-5 col-12">
              <!-- Contact -->
              <ul class="top-link">
                <li><a href="#">About</a></li>
                <li><a href="#">Doctors</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">FAQ</a></li>
              </ul>
              <!-- End Contact -->
            </div>
            <div class="col-lg-6 col-md-7 col-12">
              <!-- Top Contact -->
              <ul class="top-contact">
                <li><i class="fa fa-phone"></i>+880 1234 56789</li>
                <li><i class="fa fa-envelope"></i><a href="mailto:support@yourmail.com">support@yourmail.com</a></li>
              </ul>
              <!-- End Top Contact -->
            </div>
          </div>
        </div>
      </div>
      <!-- End Topbar -->
      <!-- Header Inner -->
      <div class="header-inner">
        <div class="container">
          <div class="inner">
            <div class="row">
              <div class="col-lg-3 col-md-3 col-12">
                <!-- Start Logo -->
                <div class="logo">
                  <a href="index.html"><img src="./img/logo.png" alt="#"></a>
                </div>
                <!-- End Logo -->
                <!-- Mobile Nav -->
                <div class="mobile-nav"></div>
                <!-- End Mobile Nav -->
              </div>
              <div class="col-lg-7 col-md-9 col-12">
                <!-- Main Menu -->
                <div class="main-menu">
                  <nav class="navigation">
                    <ul class="nav menu">

                      <div style="padding-top: 12px">
                        <el-menu
                            :default-active="activeIndex"
                            class="el-menu-demo"
                            mode="horizontal"
                            @select=""
                            router
                            text-color="#000"
                            active-text-color="#1A76D1">
                          <el-menu-item index="/dylHome" style="width: 120px;">首页</el-menu-item>

                          <el-submenu index="/" style="width: 120px;">
                            <template slot="title">服务</template>
                            <el-menu-item index="dylFunction">分类和分割</el-menu-item>
                            <el-menu-item index="dylUpload">上传数据集</el-menu-item>
                          </el-submenu>

                          <el-menu-item index="/dylHistory" style="width: 120px;">历史记录</el-menu-item>
                          <el-menu-item index="/dylPerson" style="width: 120px;">个人中心</el-menu-item>

                        </el-menu>
                      </div>
                    </ul>
                  </nav>
                </div>
                <!--/ End Main Menu -->
              </div>
              <div class="col-lg-2 col-12">
                <div class="get-quote">
                  <a href="appointment.html" class="btn">Book Appointment</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--/ End Header Inner -->
    </header>
    <div style="background-color: white; padding: 2px;" >

      <router-view/>
    </div>

  </div>

</template>

<script>
export default {
  name: "UserLayout",
  data(){
    return{
      activeIndex:1,

    }
  }
}
</script>

<style scoped>
@import 'https://fonts.googleapis.com/css?family=Poppins:200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap';
@import url('css/bootstrap.min.css');
@import url('css/nice-select.css');
@import url('css/font-awesome.min.css');
@import url('css/icofont.css');
@import url('css/slicknav.min.css');
@import url('css/datepicker.css');
@import url('css/animate.min.css');
@import url('css/magnific-popup.css');
@import url('css/normalize.css');
@import url('style.css');
@import url('css/responsive.css');
@import url('css/owl-carousel.css');

</style>