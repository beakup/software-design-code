import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout.vue'
import Cookies from "js-cookie";
import UserLayout from "@/mediplus-lite/UserLayout";
import PersonalUser from "@/views/userEdge/PersonalUser";
Vue.use(VueRouter)

const routes = [
   // =========登录==========
  {
    path: '/ruleChange',
    name: 'RuleChange',
    component: () => import('@/views/login/RuleChange.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/Login.vue')
  },
  {
    path: '/userLogin',
    name: 'UserLogin',
    component: () => import('@/views/login/UserLogin.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/login/register/Register.vue'),
  },
//===================用户页面

  {
    path: '/userLayout',
    name: 'UserLayout',
    component: () => import('@/mediplus-lite/UserLayout.vue'),
  },
  //=====用户主页
  {
    path: '/',
    name: 'UserLayout',
    component: UserLayout,
    redirect: '/ruleChange',//嵌套子路由userHome
    children: [
      {
        path: '/dylHome',
        name: 'UserHome',
        component: () => import('@/views/dyl/Home.vue'),
      },
      {
        path: '/dylPerson',
        name: 'User',
        component: () => import('@/views/dyl/person.vue'),
      },
      {
        path: '/dylHistory',
        name: 'UserHistory',
        component: () => import('@/views/dyl/history.vue'),
      },
      {
        path: '/dylFunction',
        name: 'UserFunction',
        component: () => import('@/views/dyl/classify-and-seg.vue'),
      },
      {
        path: '/dylUpload',
        name: 'UserUpload',
        component: () => import('@/views/dyl/upload.vue'),
      }

    ]
  },
  //==========管理员主页============
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/home',//嵌套子路由
    children: [//子路由
      //=================User===============
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/home/HomeView.vue'),
      },
      {
        path: 'userList',
        name: 'UserList',
        component: () => import('@/views/user/User.vue'),

      },
      {
        path: 'addUser',
        name: 'AddUser',
        component: () => import('@/views/user/AddUser.vue'),

      },
      {
        path: 'editUser',
        name: 'EditUser',
        component: () => import('@/views/user/EditUser.vue'),

      },
      //================Admin=====
      {
        path: 'adminList',
        name: 'AdminList',
        component: () => import('@/views/admin/List.vue'),
      },
      {
        path: 'addAdmin',
        name: 'AddAdmin',
        component: () => import('@/views/admin/Add.vue'),

      },
      {
        path: 'editAdmin',
        name: 'EditAdmin',
        component: () => import('@/views/admin/Edit.vue'),

      },
        //=============knowledge=========
      {
        path: 'knowledgeList',
        name: 'KnowledgeList',
        component: () => import('@/views/knowledge/List.vue'),
      },
      {
        path: 'addKnowledge',
        name: 'AddKnowledge',
        component: () => import('@/views/knowledge/Add.vue'),

      },
      {
        path: 'editKnowledge',
        name: 'EditKnowledge',
        component: () => import('@/views/knowledge/Edit.vue'),

      },
        //==================prediction==========
      {
        path: 'predictionList',
        name: 'PredictionList',
        component: () => import('@/views/prediction/List.vue'),
      },
      {
        path: 'addPrediction',
        name: 'AddPrediction',
        component: () => import('@/views/prediction/Add.vue'),

      },
      {
        path: 'editPrediction',
        name: 'EditPrediction',
        component: () => import('@/views/prediction/Edit.vue'),

      },
      {
        path: 'predictionTest',
        name: 'PredictionTest',
        component: () => import('@/views/prediction/Test.vue'),
      },
      {
        path: 'testIt',
        name: 'TestIt',
        component: () => import('@/views/prediction/test/TestIt.vue'),
      },

    ]
  },
  {
    path: 'firstPage',
    name: 'FirstPage',
    component: () => import('@/views/FirstPage.vue'),
  },

  {
    path: "*",
    component:() => import('@/views/404')
  }
]

// 解决Vue-Router升级导致的Uncaught(in promise) navigation guard问题
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this, location).catch((err) => err);
};


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
//路由守卫
/*router.beforeEach((to,from,next) => {
  if(to.path === '/ruleChange') next()
  const admin = Cookies.get("admin")
  if (!admin && to.path !== '/ruleChange' ) return next("/ruleChange")//强制退回登录界面
  //访问home时且Cookies里有数据，则放行
  next()
})*/
export default router
