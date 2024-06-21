<template>
  <div id="commentstemplate">
    <div id="comments" class="comments-list">
      <h3>" Baseline Model "</h3>
      <div class="entry alt">
        <div class="commentheader">
          <p class="avt"><img alt="" src="@/assets/SwinTransformer.png" class="avatar avatar-45 photo avatar-default" width="650" height="200"></p>
          <p class="date">Swin Transformer 2021 CVPR <a class="comment-edit-link" href="https://arxiv.org/pdf/2103.14030.pdf">More</a> </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="entry alt">
        <div class="commentheader">
          <p class="avt"><img alt="" src="@/assets/Mixformer.png" class="avatar avatar-45 photo avatar-default" width="650" height="190"></p>
          <p class="date">Mixformer CVPR 2022 oral <a class="comment-edit-link" href="https://arxiv.org/pdf/2204.02557.pdf">More</a> </p>
        </div>
        <div class="clear"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModelDesign"
}
</script>

<style scoped>
#maincontent {
  padding:25px 20px 0 12px;
}

@import url('/public/in/style.css');
</style>