<template>
  <div>
    <div id="maincontent">
      <section id="leftcontent">
        <header id="pageheader">
<!--          <h1>Roshan Ravi // Web Designer</h1>-->
          <h2 id="introduction"> <span>BreaKHis   数据集简介</span> </h2>

          <h3>1.多种类型的组织切片图像： 数据集包含来自不同病理类型和组织类型的乳腺切片图像，涵盖了正常组织、良性病变和恶性病变等不同类别。</h3>
          <h3>2.高分辨率图像： 数据集提供了高分辨率的图像，使得研究人员可以详细分析细胞结构和病理特征。</h3>
          <h3>3.多个病理标签： 每个图像都有相应的病理标签，包括病变类型、恶性程度等信息，这有助于开发自动化的病理诊断算法。<a href="https://www.cnblogs.com/Yumi5251/p/16178057.html" class="readmore">查看更多</a></h3>
        </header>
      </section>
      <section id="leftcontents">
        <header id="mainheading">
          <h2>数据预处理</h2>
        </header>
      </section>
    </div>
    <div class="data-description">
      <p>
        数据清洗：去除数据中的噪声、异常值和缺失值，以确保模型不受干扰，同时提高数据质量。
      </p>
      <p>
        归一化和标准化：将数据特征进行缩放，确保各特征具有相似的尺度，以提高模型的训练效果。常见方法包括最小-最大缩放和Z-score标准化。
      </p>
      <p>
        数据增强：通过对训练数据进行随机变换，生成更多样本以扩充数据集。对图像数据来说，数据增强包括旋转、平移、缩放、翻转等操作，有助于提升模型的鲁棒性。
      </p>
      <p>
        特征提取：对原始数据进行特征提取，将复杂的数据转化为更有代表性的特征。在深度学习中，可以使用卷积神经网络（CNN）等模型自动学习特征。
      </p>
      <p>
        数据切分：将数据集分为训练集、验证集和测试集，用于模型的训练、调参和评估。
      </p>
      <p>
        数据平衡：处理数据不平衡问题，确保各类别的样本数量均衡，以避免模型偏向于数量多的类别。
      </p>
    </div>
  </div>
</template>

<style>
.data-description {
  max-width: 600px;
  font-size: 18px;
  margin: 0 auto;
  position: absolute;
  top: 73%; /* 将元素在整体高度为 600px 处垂直居中 */
  left: 40%;
  transform: translate(-50%, -50%);
}
</style>

<style scoped>
#maincontent {
  padding:25px 15px 0 5px;
}

@import url('/public/in/style.css');
</style>