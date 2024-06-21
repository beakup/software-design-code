<template>
  <div id="maincontent">
    <section id="leftcontent" class="normalpage">
      <section id="leftcontents">
        <div id="contentwrap" class="portfolio">
          <article>
            <header>
              <h3>Benign Breast Cancer Cell Sample</h3>
            </header>
            <div class="pfitem">
              <div class="pfthumb"> <img src="@/assets/breastgood.png" width="249" height="188" alt=""></div>
              <div class="pfinfo">
                <p>  良性乳腺癌细胞是非癌性生长，不会侵犯周围组织。通常生长缓慢，不太可能扩散。定期监测和医学指导对确保其稳定行为至关重要。深度学习通过卷积神经网络等技术，可以自动识别和学习图像中的复杂特征，如细胞核形态、纹理等。这有助于医生更准确地评估细胞的状态，提供早期诊断和治疗建议。深度学习还能帮助挖掘隐藏在大量图像数据中的模式，加速医学研究，提升良性乳腺癌的诊断和治疗水平。 </p>
                <a href="https://blog.csdn.net/qq_42722197/article/details/118949236?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522169215409216800222855140%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=169215409216800222855140&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-118949236-null-null.142^v92^koosearch_v1&utm_term=%E4%B9%B3%E8%85%BA%E7%99%8C%E5%88%86%E7%B1%BB&spm=1018.2226.3001.4187" class="txtbtn">View Website</a> </div>
              <div class="clear"></div>
            </div>
          </article>
          <article>
            <header>
              <h3>Malignant Breast Cancer Cell Sample</h3>
            </header>
            <div class="pfitem">
              <div class="pfthumb"> <img src="@/assets/breastbad.png" width="249" height="188" alt=""></div>
              <div class="pfinfo">
                <p>细胞核形态特征： 恶性细胞的细胞核通常具有异型性，核大小和形状不规则。深度学习模型可以学习识别细胞核的轮廓、核仁的存在以及核质比等形态特征。</p>
                <p>核分裂和形态异常： 恶性细胞通常会表现出核分裂的现象，其核内可能存在多个核仁。模型需要检测和定位这些异常现象，有助于辨别恶性细胞。</p>
                <p></p>
                <a href="https://blog.csdn.net/bashendixie5/article/details/124999831?ops_request_misc=&request_id=&biz_id=102&utm_term=%E4%B9%B3%E8%85%BA%E7%99%8C%E5%88%86%E7%B1%BB&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-1-124999831.nonecase&spm=1018.2226.3001.4187" class="txtbtn">View Website</a> </div>
              <div class="clear"></div>
            </div>
          </article>
        </div>
      </section>
    </section>
  </div>
</template>

<script>
export default {
  name: "FeaturePick"
}
</script>

<style scoped>
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
</style>