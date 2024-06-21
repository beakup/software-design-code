<template>
<!--&lt;!&ndash;  <div style="text-align: center">&ndash;&gt;-->
<!--&lt;!&ndash;    <div style="margin-top: 150px; font-size: 100px;">404</div>&ndash;&gt;-->
<!--&lt;!&ndash;    <div style="font-size: 50px">未找到页面</div>&ndash;&gt;-->
<!--&lt;!&ndash;    <div><el-button type="text" style="margin-top: 20px;font-size: 20px" @click="$router.push('/')">返回主页</el-button></div>&ndash;&gt;-->
<!--&lt;!&ndash;  </div>&ndash;&gt;-->
  <div style="margin-left: 180px; margin-top: 0px;"> <!-- 在这里应用外边距来右移整个界面 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <section class="page_404">
      <div class="container">
        <div class="row">
          <div class="col-sm-12 ">
            <div class="col-sm-10 col-sm-offset-1  text-center">
              <div class="four_zero_four_bg">
                <h1 class="text-center">404</h1>

              </div>

              <div class="contant_box_404">
                <h3 class="h2">
                  Look like you're lost
                </h3>

                <p>the page you are looking for not avaible!</p>

                <a href="/" class="link_404">to find something</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script >
export default {
  name: "404.vue"
}
</script>

<style scoped>

/*======================
    404 page
=======================*/


.page_404{ padding:40px 0; background:#fff; font-family: 'Arvo', serif;
}

.page_404  img{ width:100%;}

.four_zero_four_bg{

  background-image: url('https://cdn.dribbble.com/users/285475/screenshots/2083086/dribbble_1.gif');
  height: 500px;
  background-position: center;
  /*width:100%;*/
}


.four_zero_four_bg h1{
  font-size:80px;
}

.four_zero_four_bg h3{
  font-size:80px;
}

.link_404{
  color: #fff!important;
  padding: 10px 20px;
  background: #39ac31;
  margin: 20px 0;
  display: inline-block;}
.contant_box_404{ margin-top:-50px;}
</style>

