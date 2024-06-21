<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">编辑用户</div>
    <el-form :inline="true" :model="form" label-width="100px">

      <el-form-item label="会员卡号">
        <el-input v-model="form.username" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="form.sex" placeholder="请输入性别"></el-input>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
      </el-form-item>
<!--      <br>-->
<!--      <el-form-item label="上传头像" prop="cover">-->
<!--        <el-upload-->
<!--            class="avatar-uploader"-->
<!--            :action="'http://localhost:9090/api/userEdge/file/upload?token='+this.userEdge.token"-->
<!--            :show-file-list="false"-->
<!--            :on-success="handleCoverSuccess"-->
<!--        >-->
<!--          <img v-if="form.cover" :src="form.cover" class="avatar">-->
<!--          <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--        </el-upload>-->
<!--      </el-form-item>-->
    </el-form>

    <div style="text-align: center;margin-top: 30px">
      <el-button type="primary" @click="save" size="medium">提交</el-button>
      <!--      <el-button type="danger">返回列表</el-button>-->
    </div>
    <div style="text-align: center;margin-top: 30px">
      <el-popconfirm

          title="您确定注销账号吗？"
          @confirm="del(scope.row.id)"
      >
        <el-button type="danger" slot="reference" size="medium">注销</el-button>
      </el-popconfirm>
    </div>

  </div>
</template>

<script>
import request from "@/utils/userRequest";
import axios from 'axios';
import Cookies from "js-cookie";
export default {
  name: 'ChangeInfo',
  data(){
    return{
      dialogVisible: false,
      userEdge: Cookies.get('userEdge') ? JSON.parse(Cookies.get('userEdge')) : {}, //获取token
      tableData:[],
      total:0,
      form:{
        id:Number,
        name:"",
        username:"",
        address:"",
        phone:"",
        sex:"",
        age:Number,
        createtime:"",
        updatetime:"",
        cover:"",
      },
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        phone: ''
      }

    }
  },
  created() {
    this.load()
  },
  methods: {/*将数据库传到前端*/
    handleCoverSuccess(res) {
      if(res.code=='200'){
        console.log(res)
        this.form.cover=res.data
        // this.$set(this.form,"cover",res.data.cover) //强制设置
      }
    },
    load() {
      // fetch( 'http://localhost:9090/userEdge/list').then(res => res.json()).then(res => {
      //   console.log(res)
      //   this.tableData = res//.then(res => res.json()).
      // })
      console.log(this.userEdge.id)
      request.get("userEdge/userList/" + this.userEdge.id,{
        params: this.params
      }).then(res => {
        console.log(res);
        this.form = res.data;
        // this.id = res.data.id;
        // this.username = res.data.username;
        // this.name = res.data.name;
        // this.address = res.data.address;
        // this.phone = res.data.phone;
        // this.sex = res.data.sex;
        // this.age = res.data.age;
        // this.createtime = res.data.createtime;
        // this.updatetime = res.data.updatetime;
        // this.total = res.data.total
      })
    },
    //   request.get('/userEdge/page',{
    //     params: this.params
    //   }).then(res => {
    //     if(res.code === "200"){
    //       this.tableData = res.data.list
    //       this.total = res.data.total
    //     }
    //   })
    // },
    save() {
      request.put('/userEdge/update',this.form).then(res => {
        if(res.code === '200') {
          this.$notify.success('更新成功')
          //this.$router.push("/userList")
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    del(id) {
      request.delete("/userEdge/delete/" + id).then(res => {
        if(res.code === "200"){
          this.$notify.success('注销成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    handleClose() {
      this.dialogVisible = false;
      this.$emit("flesh");
    },
  }
}
</script>

<style scoped>
.img {
  width: 80px;
  height: 80px;
}
</style>