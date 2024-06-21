<template>
  <div id="maincontent">
    <section id="leftcontent" class="normalpage">
      <section id="leftcontents">
        <div id="contentwrap" class="portfolio">
          <article>
            <header>
              <h3>相关模型测试图</h3>
            </header>
            <el-carousel :interval="2000" type="card" height="260px">
              <el-carousel-item v-for="(carouselItem, index) in item" :key="index">
                <img :src="require(`@/assets/${carouselItem}`)" class="carousel-image" alt="Carousel Image" />
              </el-carousel-item>
            </el-carousel>
          </article>
        </div>


<!--        <div id="bottom">-->
<!--          <div id="btmwrap">-->
<!--            <section id="briefbio">-->

<!--              <h2>已实验模型 * accuracy</h2>-->
<!--              <h>基于2536张乳腺细胞图数据（train:1831张，val:474，test:231):</h>-->
<!--              <p>Resnet18  * 74.05 %</p>-->
<!--              <p>Mixformer * 90.04 %</p>-->
<!--              <p>MobileViT * 86.58 %</p>-->
<!--              <p>More...</p>-->
<!--            </section>-->
<!--            <section id="latestproject">-->
<!--              <h2>who ?</h2>-->
<!--              <div class="portfoliopre">-->
<!--                <div class="pf-thumb"> <img src="@/assets/inFo.jpg" width="220" height="155" alt="" /> </div>-->
<!--                <div class="clear"></div>-->
<!--              </div>-->
<!--            </section>-->
<!--          </div>-->
<!--          <div class="clear"></div>-->
<!--        </div>-->
        <div id="commentstemplate">
          <div id="comments" class="comments-list">
            <h3>The test images for the above model are provided by the Kaggle platform for support, and the accuracy is provided for reference purposes only.</h3>
          </div>
        </div>
      </section>
    </section>
  </div>
</template>

<script>
export default {
  name: "ModelTrain",
  data() {
    return {
      item: [
              "MixformerLoss.png",
          "MixformerComfusion.png",
          "MobileViT.png","MobileConfusion.png",
          "Resnet18.png"
      ]
    }
  }
}

</script>

<style scoped>
.carousel-image {
  background-size: cover; /* 背景契合图像大小 */
  background-position: center center;
  background-repeat: no-repeat;
  width: 100%;
  height: 100%;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.pfitem .pfthumb {
  width:302px;
  height:228px;
  position:absolute;
  left:10px;
  top:1%;
}
#maincontent {
  padding:25px 15px 0 5px;
  /*上下左右*/
}

@import url('/public/in/style.css');
/*@import url('/public/on/css/bootstrap.min.css');*/
/*@import url('/public/on/css/bootstrap-theme.min.css');*/
/*@import url('/public/on/css/fontAwesome.css');*/
/*@import url('/public/on/css/hero-slider.css');*/
/*@import url('/public/on/css/owl-carousel.css');*/
/*@import url('/public/on/css/datepicker.css');*/
/*@import url('/public/on/css/tooplate-style.css');*/
</style>