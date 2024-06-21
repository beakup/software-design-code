<template>
  <div>
    <el-card>
      <el-descriptions class="margin-top" title="简介" :column="2" border>
<!--        <el-descriptions-item>-->
<!--          <template slot="label">-->
<!--            <i class="el-icon-picture-outline"></i>-->
<!--            头像-->
<!--          </template>-->
<!--          <img class="img" :src="cover" alt="" />-->
<!--        </el-descriptions-item>-->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            账户名
          </template>
          {{ username }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>
            昵称
          </template>
          {{ name }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-odometer"></i>
            年龄
          </template>
          {{ age }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-male"></i>
            <i class="el-icon-female"></i>
            性别
          </template>
          <el-tag size="medium">{{ sex }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号码
          </template>
          {{ phone }}
        </el-descriptions-item>


        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-date"></i>
            注册日期
          </template>
          {{ createtime }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-date"></i>
            更新日期
          </template>
          {{ updatetime }}
        </el-descriptions-item>
      </el-descriptions>
    </el-card>

<!--    <div class="id">-->
<!--      <span> {{ id }} </span>-->
<!--    </div>-->
<!--    <div class="name">-->
<!--      <span> {{ name }} </span>-->
<!--    </div>-->
<!--    <div class="username">-->
<!--      <span> {{ username }} </span>-->
<!--    </div>-->
<!--    <div class="age">-->
<!--      <span> {{ age }} </span>-->
<!--    </div>-->
<!--    <div class="address">-->
<!--      <span> {{ address }} </span>-->
<!--    </div>-->
<!--    <div class="phone">-->
<!--      <span> {{ phone }} </span>-->
<!--    </div>-->
<!--    <div class="sex">-->
<!--      <span> {{ sex }} </span>-->
<!--    </div>-->
<!--    <div class="createtime">-->
<!--      <span> {{ createtime }} </span>-->
<!--    </div>-->
<!--    <div class="updatetime">-->
<!--      <span> {{ updatetime}} </span>-->
<!--    </div>-->
  </div>
</template>

<script>
import request from "@/utils/userRequest";
import axios from 'axios';
import Cookies from "js-cookie";
export default {
  name: 'BaseInfo',
  data(){
    return{
      dialogVisible: false,
      userEdge: Cookies.get('userEdge') ? JSON.parse(Cookies.get('userEdge')) : {}, //获取token
      tableData:[],
      total:0,
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
        //this.form = res.data;
        this.id = res.data.id;
        this.username = res.data.username;
        this.name = res.data.name;
        this.address = res.data.address;
        this.phone = res.data.phone;
        this.sex = res.data.sex;
        this.age = res.data.age;
        this.createtime = res.data.createtime;
        this.updatetime = res.data.updatetime;
        this.total = res.data.total
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