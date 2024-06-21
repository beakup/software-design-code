
<template>
  <div>
    <input type="file" ref="fileInput" @change="handleFileChange" />
    <button @click="uploadFile" :disabled="!fileUploaded">上传并分类</button>
    <div v-if="classificationResult">
      <el-dialog title="初步分类结果" :visible="showDialog" @close="handleDialogClose">
        <p>benign 概率：{{ classificationResult.class1Probability | percentage }}</p>
        <p>malignant 概率：{{ classificationResult.class2Probability | percentage }}</p>
      </el-dialog>
<!--      <p>benign 概率：{{ classificationResult.class1Probability | percentage }}</p>-->
<!--      <p>malignant 概率：{{ classificationResult.class2Probability | percentage }}</p>-->
    </div>
    <button @click="showInputPrompt">诊断结果</button>
  </div>
</template>
<script>
import axios from 'axios'; //用于前端
import request from "@/utils/request";
import Swal from 'sweetalert2';
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
      showDialog: false // 控制弹窗的显示与隐藏
    };
  },
  methods: {
    handleFileChange(event) {
      this.file = event.target.files[0];
      this.fileUploaded = true; // 文件上传后，设置标志为true
    },
    async uploadFile() {
      const formData = new FormData();
      formData.append('image', this.file);
      try {
        const response = await request.post('/api/classify', formData);
        //console.log("123",response.data)
        this.classificationResult = response;
        this.showDialog = true; // 显示弹窗
      } catch (error) {
        console.error('Error uploading and classifying image:', error);
      };
    },
    async showInputPrompt() {
      const {value: text} = await Swal.fire({
        title: '诊断结果',
        input: 'textarea',
        inputLabel: '请结合其他病理结果谨慎判断',
        inputPlaceholder: '在这里输入诊断结果...',
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
    handleDialogClose() {
      this.showDialog = false; // 关闭弹窗
      this.classificationResult = null; // 清空分类结果数据
      this.file = null; // 清空上传的文件
      this.fileUploaded = false; // 重置文件上传状态
    }
  }
}
</script>

