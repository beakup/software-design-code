<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">编辑模型</div>
    <el-form :inline="true" :model="form" label-width="100px">
      <el-form-item label="模型名" prop="username">
        <el-input v-model="form.username" placeholder="请输入模型名"></el-input>
      </el-form-item>

      <el-form-item label="模型代码" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入模型代码及内容"></el-input>
      </el-form-item>
      <el-form-item label="模型备注">
        <el-input v-model="form.email" placeholder="请输入模型备注"></el-input>
      </el-form-item>
      <el-form-item label="乳腺细胞测试图" prop="cover">
        <el-input v-model="form.cover" placeholder="请上传乳腺细胞图"></el-input>
        <img v-if="form.cover" :src="form.cover" class="avatar" width="40%">
      </el-form-item>
    </el-form>


      <div style="text-align: center;margin-top: 30px">
      <el-button type="primary" @click="save" size="medium">提交</el-button>
      <!--      <el-button type="danger">返回列表</el-button>-->
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'EditPrediction',
  data(){
    return {
      // admin:Cookies.get('admin')?JSON.parse(Cookies.get('admin')):{},
      // prediction: Cookies.get('prediction') ? JSON.parse(Cookies.get('prediction')) : {},
      form: {}
    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/Prediction/" + id).then(res =>{
      this.form = res.data
    })
  },
  methods: {
    // handleCoverSuccess(res) {
    //   if(res.code === '200') {
    //     this.form.cover = res.data
    //   }
    // },
    // handleCodeSuccess(res) {
    //   if(res.code=='200'){
    //     console.log(res)
    //     this.form.phone=res.data
    //     // this.$set(this.form,"cover",res.data.cover) //强制设置
    //   }
    // },
    save() {
      request.put('/prediction/update',this.form).then(res => {
        if(res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/predictionList")
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style>
/*.avatar-uploader .el-upload {*/
/*  border: 1px dashed #d9d9d9;*/
/*  border-radius: 6px;*/
/*  cursor: pointer;*/
/*  position: relative;*/
/*  overflow: hidden;*/
/*}*/
/*.avatar-uploader .el-upload:hover {*/
/*  border-color: #409EFF;*/
/*}*/
/*.avatar-uploader-icon {*/
/*  font-size: 28px;*/
/*  color: #8c939d;*/
/*  width: 178px;*/
/*  height: 178px;*/
/*  line-height: 178px;*/
/*  text-align: center;*/
/*}*/
/*.avatar {*/
/*  width: 178px;*/
/*  height: 178px;*/
/*  display: block;*/
/*}*/
</style>
