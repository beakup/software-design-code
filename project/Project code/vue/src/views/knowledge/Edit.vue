<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">编辑文章</div>
    <el-form :inline="true" :model="form" label-width="100px">
      <el-form-item label="文章名称" prop="username">
        <el-input v-model="form.username" placeholder="请输入文章名称"></el-input>
      </el-form-item>

      <el-form-item label="文章简介">
        <el-input v-model="form.email" placeholder="请输入文章简介"></el-input>
      </el-form-item>

      <el-form-item label="文章封面" prop="path">
        <el-input v-model="form.phone" placeholder="请上传文章封面"></el-input>
        <img v-if="form.phone" :src="form.phone" class="avatar" width="40%">
      </el-form-item>
      <el-form-item label="文章路径">
        <el-input v-model="form.path" placeholder="请输入文章路径"></el-input>
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

export default {
  name: 'EditKnowledge',
  data(){
    return {
      form: {},
    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/knowledge/" + id).then(res =>{
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('/knowledge/update',this.form).then(res => {
        if(res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/knowledgeList")
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  },
}
</script>