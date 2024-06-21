<template>
  <div style="width:100%;height:calc(100vh - 54.8px - 76.8px); background-color: white;float: left;position:relative;" class="demo-search-div">

    <!--  左边-->
    <div style="width: 30%;margin-left: 15%;float: left;margin-top: 2%;">
      <el-chart class="box-card1">
        <div style="height: 8%">
          <div class="txt1">个人信息</div>
          <el-divider ></el-divider>
        </div>
        <div style="height: 67%;display: inline-block;vertical-align: middle;padding-left: 7%;padding-right: 6%">
          <img :src="image_person"  alt=""
               style="border-radius: 100%; width: 100%;height: 100%;">
        </div>
        <div style="height: 7%;">
          <el-divider></el-divider>
          <span class="txt" >昵称</span>
          <span class="txt_tmp">{{person.username}}</span>
        </div>
        <div style="height: 7%;">
          <el-divider ></el-divider>
          <div class="txt">上次登录</div>
          <div class="txt_tmp">{{person.logintime}}</div>
        </div>
        <div style="height: 8%;">
          <el-divider ></el-divider>
          <div class="txt">加入时间</div>
          <div class="txt_tmp">{{person.createtime}}</div>
        </div>
      </el-chart>
    </div>

    <!--  右边-->

    <div style="width: 35%;float: left;margin-left: 50%;">
      <el-chart class="box-card2">
        <div style="padding-top: 1%;">（在此处编辑个人信息）</div>
        <div class="name">个人信息</div>

        <div style="width: 75%; margin-left: 10%; color:#12B7F5; font-size: 18px; ">
          <el-form label-width="80px" size="small"  :model="person" :rules="rules" ref="ruleForm">

            <el-form-item label="用户名" prop="username">
              <el-input v-model="person.id" placeholder="请输入用户名" disabled ></el-input>
            </el-form-item>

            <el-form-item label="姓名" prop="name">
              <el-input v-model="person.name" placeholder="请输入用户名"  ></el-input>
            </el-form-item>

            <el-form-item label="邮箱" prop="email">
              <el-input v-model="person.email" placeholder="请输入邮箱（比如3554891145@qq.com)"></el-input>
            </el-form-item>

            <el-form-item label="电话号码" prop="phone">
              <el-input v-model="person.phone" placeholder="请输入正确电话号码（比如19152002793）"></el-input>
            </el-form-item>

            <el-form-item label="职业" prop="email">
              <el-input v-model="person.occupation" placeholder=""></el-input>
            </el-form-item>

            <el-form-item label="工作单位" prop="email">
              <el-input v-model="person.workplace" placeholder=""></el-input>
            </el-form-item>

            <el-form-item label="城市" prop="email">
              <el-input v-model="person.address" placeholder=""></el-input>
            </el-form-item>

          </el-form>
        </div>

        <div style="margin-left: 40%;">
          <el-row>
            <el-button type="success" @click="save">保存</el-button>
            <el-button type="info" @click="load">取消</el-button>
          </el-row>
        </div>

      </el-chart>
    </div>

  </div>
</template>

<script>
import Cookies from "js-cookie";
import request from "@/utils/request";
export default {
  name: "person",
  data(){
    const checkEmail = (rule, value, callback) => {
      if(value){
        if(!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value)){
          callback(new Error('请输入合理的邮箱'));
        }
      }
      callback();
    };
    const checkPhone = (rule, value, callback) => {
      if(value){
        if(!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)){
          callback(new Error('请输入合理的电话号码'));
        }
      }
      callback();
    };
    return{
      userEdge:Cookies.get("userEdge") ? JSON.parse(Cookies.get("userEdge")) : {},
      image_person:require('@/assets/person.png'),
      person:{
        id:null,
        name:"",
        email:"",
        phone:"",
        occupation:"",
        workplace:"",
        address:"",
        lasttime:"",
        logintime:"",
        cover:""
      },
      rules:{
        email:[
          { validator: checkEmail, trigger: 'blur' }
        ],
        phone:[
          { validator: checkPhone, trigger: 'blur' }
        ],
      }

    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("userEdge/"+this.userEdge.id).then(res=>{
        this.person = res.data
      })
    },
    save(){
      request.post("userEdge/update",this.person).then(res=>{
        if(res.code === "200"){
          this.$message.success("保存成功")
        }
      })
    },
  }
}
</script>

<style scoped>

.box-card1 {
  border: 2px solid #E5E5E5;
  border-radius: 1%;
  width: 30%;
  height: 92%;
  position: absolute;
}

.box-card2 {
  border: 2px dashed #12B7F5;
  width: 40%;
  height: 92%;
  position: absolute;
  box-shadow: 10px 10px 4px 1px #C0BDC1;
}
.demo-search-div >>> .el-divider--horizontal{
  margin-top: 1%;
  margin-bottom: 1%;
}
.txt1{
  margin: 2%;
  font-size: 100%;
  width: 30%;
}
.txt{
  margin: 2%;
  font-size: 100%;
  width: 30%;
  float: left;
}
.name{
  font-size: 20px;
  color: #2f60bd;
  margin: 3% 0% 3% 2%;
}
.txt_tmp{
  float: right;
  margin-right: 2%;
}
.sub_txt{
  font-size: 16px;
  color:#12B7F5 ;
}

</style>