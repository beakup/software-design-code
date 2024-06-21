
<template>
  <div>
    <div id="maincontent">
      <section id="leftcontent">
        <header id="pageheader">
          <h1> 声明</h1>
          <h2 id="introduction"> <span>亲爱的朋友,</span>
            我正在开发一个乳腺癌检测系统，使用深度学习技术来尝试提高乳腺癌的早期检测。然而，我需要您理解，目前该系统仍处于实验阶段，只对乳腺癌进行初步定性（良/恶性），
            进一步的诊断结果（比如癌症亚型）必要时可咨询医生。</h2>
        </header>
        <section id="leftcontents">
          <header id="mainheading">
            <h2>请上传乳腺切片图进行检测</h2>
          </header>
          <input type="file" ref="fileInput" @change="handleFileChange" />
          <button @click="uploadFile" :disabled="!fileUploaded">检测开始</button>
          <div v-if="classificationResult">
            <el-dialog title="初步诊断结果" :visible="showDialog" @close="handleDialogClose">
              <p>benign 概率：{{ classificationResult.class1Probability | percentage }}</p>
              <p>malignant 概率：{{ classificationResult.class2Probability | percentage }}</p>
              <p v-if="classificationResult.class1Probability > classificationResult.class2Probability">
                初步诊断结果为良性！这个好消息是对你健康生活方式的最佳认可，你的坚持和努力都在取得成果。继续保持健康的饮食习惯、适度的运动以及积极的心态，
                它们是你宝贵的资产，将会在日常生活中为你带来更多的幸福与快乐。继续配合治疗，相信很快就可以痊愈滴！
              </p>
              <p v-else>
                诊断结果为恶性。
                面对恶性的诊断结果，我理解你可能会感到困惑、恐惧和焦虑。然而，我希望你知道，你并不孤单，我们将一直在你身边，为你提供全力支持和关怀。
                在这个时刻，积极的态度和心理支持是非常重要的。请与你的家人、朋友和医疗团队保持紧密联系，分享你的感受和想法。他们会成为你的坚实后盾，为你带来温暖和力量。
              </p>
            </el-dialog>
          </div>
          <button @click="showInputPrompt">病情反馈</button>
        </section>
      </section>

    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2';
import axios from 'axios'; //用于前端
import request from "@/utils/request";

export default {
  filters: {
    percentage(value) {
      if (typeof value !== 'number') {
        return value;
      }
      return (value * 100).toFixed(1) + '%';
    }
  },
  data() {
    return {
      file: null,
      classificationResult: null,
      fileUploaded: false, // 添加标志变量
      showDialog: false, // 控制弹窗的显示与隐藏
      path:"C:\\Users\\86191\\Desktop\\"
    };
  },
  methods: {
    handleFileChange(event) {
      this.file = event.target.files[0];
      this.fileUploaded = true; // 文件上传后，设置标志为true
    },
    async showInputPrompt() {
      const {value: text} = await Swal.fire({
        title: '病况描述',
        input: 'textarea',
        inputLabel: '为了精准治疗，请谨慎输入病理现象哦',
        inputPlaceholder: '在这里输入病况...',
        inputAttributes: {
          'aria-label': 'Type your message here'
        },
        showCancelButton: true
      });

      if (text) {
        // Swal.fire(text);
        Swal.fire({
          title: text,
          width: 600,
          padding: '3em',
          color: '#716add',
          background: '#fff url(@/assets/info.jpg)',
          backdrop: `
          rgba(0,0,0,0.4)
          url('https://cdn.pixabay.com/animation/2023/02/28/14/20/14-20-09-131_512.gif')
          left top
          no-repeat
          `
        })
      }
    },
     uploadFile() {
      const formData = new FormData();

      formData.append('image', this.file);
      console.log("res",this.file);
      try {
        // const response =  request.post('/api/classify', formData
        // ).then(res =>{
        // });
        // console.log(this.path+this.file.name,"+++++++")

        request.get('/classify/detectOutWithCache',{
          params: {
            imageLink:this.path+this.file.name
          }
        }).then(res => {
          if(res.code === "200"){
            console.log(res,"+++++++++++++++++++++++++++++++++")
          }
        })

        this.classificationResult = 0.2;
        this.showDialog = true; // 显示弹窗
      } catch (error) {
        console.error('Error uploading and classifying image:', error);
      };
    },
    handleDialogClose() {
      this.showDialog = false; // 关闭弹窗
      this.classificationResult = null; // 清空分类结果数据
      this.file = null; // 清空上传的文件
      this.fileUploaded = false; // 重置文件上传状态
    }
  }
}
</script>

<style scoped>
@import url('/public/in/style.css');
</style>