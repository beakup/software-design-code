<template>
  <div id="login">

  </div>
</template>


<style scoped>
.item {
  margin: 10px 0;
  color: lightgrey;
}
#login {
  font-family: "Montserrat", sans-serif;
  background: #f6f5f7;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  /* margin: -20px 0 50px; */
  /* background-image: url("https://www.17sucai.com/preview/1749733/2019-06-22/%E7%99%BB%E5%BD%95/img/img1.png"); */
  background-image: url('https://w.wallhaven.cc/full/zy/wallhaven-zy5y1o.jpg');
  /*https://img.tukuppt.com/ad_preview/00/09/01/5c990e573d984.jpg!/fw/980*/
  background-size: cover;
}


</style>

<!--
<template>
  <div>
    <div>用户名：{{ admin.username }}</div>
    <div>手机号：{{ admin.phone }}</div>
    <div>邮箱：{{ admin.email }}</div>
  </div>
</template>

<script>
import Cookies from 'js-cookie';
import request from "@/utils/request";

  export default {
    data() {
      return {
        admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
      }
    },
    created() {
      // request.get('/admin/' + this.admin.id).then(res => {
      //   this.admin = res.data
      // })
    }
  }
</script>

<style>
.item {
  margin: 10px 0;
  color: lightgrey;
}
</style>-->
