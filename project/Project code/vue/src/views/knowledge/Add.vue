<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">新增文章</div>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="文章名称" prop="username">
        <el-input v-model="form.username" placeholder="请输入文章名称"></el-input>
      </el-form-item>
      <el-form-item label="文章简介" prop="email">
        <el-input v-model="form.email" placeholder="请输入文章简介"></el-input>
      </el-form-item>

<!--      <el-form-item label="文章内容">-->
<!--        <el-input v-model="form.phone" placeholder="请输入文章内容"></el-input>-->
<!--      </el-form-item>-->
      <br>
      <el-form-item label="文章内容" prop="path">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:9090/api/knowledge/codefile/upload?token='+this.admin.token"
            :show-file-list="false"
            :on-success="handleCodeSuccess"
        >
          <img v-if="form.path" :src="form.path" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <!--            冒号表示属性绑定-->
      <br>
      <el-form-item label="文章封面" prop="phone">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:9090/api/knowledge/file/upload?token='+this.admin.token"
            :show-file-list="false"
            :on-success="handleCoverSuccess"
        >
          <img v-if="form.phone" :src="form.phone" class="avatar" width="40%">
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
  name: 'AddKnowledge',

  data(){
    return {
      admin:Cookies.get('admin')?JSON.parse(Cookies.get('admin')):{},
      knowledge: Cookies.get('knowledge') ? JSON.parse(Cookies.get('knowledge')) : {},
      form: {path:'',phone:''},
      rules: {
        username: [
          {required: true, message: '请输入文章名称', trigger: 'blur'},
          { min: 3,max: 20,message: '长度为3-20个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    handleCoverSuccess(res) {
      if(res.code=='200'){
        console.log(res)
        this.form.phone=res.data
        // this.$set(this.form,"cover",res.data.cover) //强制设置
      }
    },
    handleCodeSuccess(res) {
      if(res.code=='200'){
        console.log(res)
        this.form.path=res.data
        // this.$set(this.form,"cover",res.data.cover) //强制设置
      }

    },
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/knowledge/save', this.form).then(res => {
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