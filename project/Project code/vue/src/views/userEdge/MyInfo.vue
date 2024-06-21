<template>
  <div id="maincontent">
    <section id="leftcontent" class="normalpage">
      <section id="leftcontents">
        <header id="mainheading">
          <h2>
            Give me advice</h2>
        </header>
        <div class="comments-form">
<!--          <h3 id="respond">Add A Comment</h3>-->
          <form action="#" method="post" id="commentform">
            <p>
              <textarea name="comment" id="comment" cols="91" rows="10" tabindex="4"></textarea>
            </p>
            <p>
              <input name="submit" id="submit" tabindex="5" value="Submit Comment" src="/public/in/images/addcomment.jpg" class="button" type="image">
            </p>
            <input name="comment_post_ID" value="134" type="hidden">
            <input id="_wp_unfiltered_html_comment" name="_wp_unfiltered_html_comment" value="ae4747e828" type="hidden">
          </form>
        </div>

        <div id="bottom">
          <div id="btmwrap">
            <section id="briefbio">

              <h2>关于项目的声明：</h2>
              <p>用于训练模型的2536张乳腺乳腺组织切片数据来自公开数据集BreaKHis中100×,200×</p>
              <p>-------------------------------</p>
              <p>模型的精度待优化......</p>
              <p>-------------------------------</p>
              <p>网站只针对乳腺癌良恶性做初步检测，仅作为初期参考</p>

            </section>
            <section id="latestproject">
              <h2>who ?</h2>
              <div class="portfoliopre">
                <div class="pf-thumb"> <img src="@/assets/inFo.jpg" width="220" height="155" alt="" /> </div>
                <div class="clear"></div>
              </div>
            </section>
          </div>
          <div class="clear"></div>
        </div>
      </section>
    </section>
  </div>
</template>

<script>
export default {
  name: "MyInfo"
}
</script>

<style scoped>
#maincontent {
  padding:25px 20px 0 12px;
}
#btmwrap {
  width:800px;
  padding:70px 25px 0 25px;
  margin:auto;
}
#bottom #latestproject {
  width:60%;
  padding:0 10px 0 10px;
  float:right;
  display:block;
}
.pf-thumb {
  background-image: url('https://w.wallhaven.cc/full/zy/wallhaven-zy5y1o.jpg') ;
  height:160px;
  width:258px;
  float:left;
  padding:9px 0 0 10px;
}
@import url('/public/in/style.css');
/*@import url('/public/in/style.css');*/
</style>