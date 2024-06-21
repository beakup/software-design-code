<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">新增模型</div>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="模型名称" prop="username">
        <el-input v-model="form.username" placeholder="请输入模型名称"></el-input>
      </el-form-item>
      <el-form-item label="模型备注" prop="email">
        <el-input v-model="form.email" placeholder="请输入模型备注"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="模型代码" prop="phone">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:9090/api/prediction/codefile/upload?token='+this.admin.token"
            :show-file-list="false"
            :on-success="handleCodeSuccess"
        >
          <img v-if="form.phone" :src="form.phone" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>


      <br>
      <el-form-item label="乳腺细胞图片" prop="cover">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:9090/api/prediction/file/upload?token='+this.admin.token"
            :show-file-list="false"
            :on-success="handleCoverSuccess"
           >
          <img v-if="form.cover" :src="form.cover" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
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
  name: 'AddPrediction',

  data(){
    return {
      admin:Cookies.get('admin')?JSON.parse(Cookies.get('admin')):{},
      prediction: Cookies.get('prediction') ? JSON.parse(Cookies.get('prediction')) : {},
      form: {cover:'',file:''},
      rules: {
        username: [
          {required: true, message: '请输入模型名称', trigger: 'blur'},
          { min: 3,max: 20,message: '长度为3-20个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    handleCoverSuccess(res) {
      if(res.code=='200'){
        console.log(res)
        this.form.cover=res.data
        // this.$set(this.form,"cover",res.data.cover) //强制设置
      }
    },
    handleCodeSuccess(res) {
      if(res.code=='200'){
        console.log(res)
        this.form.phone=res.data
        // this.$set(this.form,"cover",res.data.cover) //强制设置
      }

    },
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/prediction/save', this.form).then(res => {
            console.log(res)
            if (res.code === '200') {
              this.$notify.success('新增成功')
              this.$refs['ruleForm'].resetFields()
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>


<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
