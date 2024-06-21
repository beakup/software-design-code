<template>
  <div >
    <div id="maincontent">
      <div>用户名：{{ userEdge.name }}</div>
      <section id="leftcontent">
        <header id="pageheader">
          <h1> Health & Happiness</h1>
          <h2 id="introduction"> <span>Dear Soul,</span> You'll overcome this challenge. Your strength will conquer all difficulties. Maintain a positive attitude, care for yourself, and rediscover the beauty of life. Remember, there are always people by your side supporting you, no matter when.</h2>
        </header>
        <section id="leftcontents">
          <header id="mainheading">
            <h2>
              Breast Cancer Awareness and Education</h2>
          </header>
          <div id="contentwrap">
            <section class="postinfo">
              <p class="postdata postdate">
                <time datetime="2010-05-09T14:28-08:00">  16 August</time>
              </p>
<!--              <P class="postdata">By LX</P>-->
            </section>
            <article class="postpre">
              <header>
                <h3>Health Tips!</h3>
              </header>
              <p>预防乳腺癌的关键在于健康生活方式。保持均衡饮食，摄入丰富蔬果和纤维，减少高脂食物。每周适度运动，维持适当体重，避免肥胖。定期进行乳腺自检，定期体检，及早发现异常。限制饮酒，最好不吸烟。避免长期暴露于激素，注意环境污染。积极管理心理压力，保持积极心态。定期咨询医生，获取最新健康信息，制定适合个人的预防策略。<a href="https://www.cn-healthcare.com/articlewm/20210719/content-1244415.html" class="readmore">查看更多</a></p>
            </article>
          </div>
        </section>
      </section>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie';
import request from "@/utils/request";

  export default {
    name: "UserHomeView.vue",
    data() {
      return {
        userEdge: Cookies.get('userEdge') ? JSON.parse(Cookies.get('userEdge')) : {}
      }
    },
    // created() {
    //   request.get('/userEdge/' + this.userEdge.id).then(res => {
    //     this.userEdge = res.data
    //   })
    // }
  }
</script>

<style>
.item {
  margin: 10px 0;
  color: lightgrey;
}
#maincontent {
  padding:25px 20px 0 12px;
}
@import url('/public/in/style.css');
</style>