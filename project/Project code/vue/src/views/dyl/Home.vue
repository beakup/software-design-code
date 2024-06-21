<template>
  <div style="position: relative">

    <el-carousel height="580px">
      <el-carousel-item v-for="item in imgArray" :key="item">
        <div>
          <div class="container" style="position: absolute; padding-top: 150px;padding-left: 100px; ">
            <div class="row">
              <div class="col-lg-7">
                <div class="text">
                  <h1>We Provide <span>Medical</span> Services That You Can <span>Trust!</span></h1>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris sed nisl pellentesque, faucibus libero eu, gravida quam. </p>
                  <div class="button" style="padding-top: 20px; width: 700px;display: flex">
                    <div style="width: 200px;"><a href="#" class="btn" >Get Appointment</a></div>
                    <div style="width: 200px;"><a href="#" class="btn primary">Conatct Now</a></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <img :src="item" class="rightImg">
        </div>
      </el-carousel-item>
    </el-carousel>

    <div style="position: relative">
      <!-- Start Schedule Area -->
      <section class="schedule">
        <div class="container">
          <div class="schedule-inner">
            <div class="row">
              <div class="col-lg-4 col-md-6 col-12 ">
                <!-- single-schedule -->
                <div class="single-schedule first">
                  <div class="inner">
                    <div class="icon">
                      <i class="fa fa-ambulance"></i>
                    </div>
                    <div class="single-content">
                      <span>Lorem Amet</span>
                      <h4>Emergency Cases</h4>
                      <p>Lorem ipsum sit amet consectetur adipiscing elit. Vivamus et erat in lacus convallis sodales.</p>
                      <a href="#">LEARN MORE<i class="fa fa-long-arrow-right"></i></a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-4 col-md-6 col-12">
                <!-- single-schedule -->
                <div class="single-schedule middle">
                  <div class="inner">
                    <div class="icon">
                      <i class="icofont-prescription"></i>
                    </div>
                    <div class="single-content">
                      <span>Fusce Porttitor</span>
                      <h4>Doctors Timetable</h4>
                      <p>Lorem ipsum sit amet consectetur adipiscing elit. Vivamus et erat in lacus convallis sodales.</p>
                      <a href="#">LEARN MORE<i class="fa fa-long-arrow-right"></i></a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-4 col-md-12 col-12">
                <!-- single-schedule -->
                <div class="single-schedule last">
                  <div class="inner">
                    <div class="icon">
                      <i class="icofont-ui-clock"></i>
                    </div>
                    <div class="single-content">
                      <span>Donec luctus</span>
                      <h4>Opening Hours</h4>
                      <ul class="time-sidual">
                        <li class="day">Monday - Fridayp <span>8.00-20.00</span></li>
                        <li class="day">Saturday <span>9.00-18.30</span></li>
                        <li class="day">Monday - Thusday <span>9.00-15.00</span></li>
                      </ul>
                      <a href="#">LEARN MORE<i class="fa fa-long-arrow-right"></i></a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!--/End Start schedule Area -->
    </div>


  </div>



</template>

<script>
export default {
  name: "UserHome",
  data() {
    return {
      imgArray: [
        require('@/assets/dylImg/slider2.jpg'),
        require('@/assets/dylImg/slider3.jpg'),
        require('@/assets/dylImg/slider.jpg'),
      ]
    }
  }
}
</script>

<style scoped>

.rightImg {
  width:100%;
  /*height:100%;*/
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>