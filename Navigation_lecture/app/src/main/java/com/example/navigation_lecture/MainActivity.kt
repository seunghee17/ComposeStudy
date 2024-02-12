package com.example.navigation_lecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation_lecture.ui.theme.Navigation_lectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation_lectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}
//2번
enum class NAV_ROUTE(val routeName:String, val description:String, val btnColor: Color){
    MAIN("MAIN","메인",Color.Black),
    LOGIN("LOGIN","로그인",Color.Red),
    REGISTER("REGISTER","회원가입",Color.Green),
    USER_PROFILE("USER_PROFILE","유저프로필",Color.Gray),
    SETTING("SETTING","환경설정",Color.DarkGray)
}

//3번
//네비게이션 라우트 액션
class RouteAction(navHostController : NavHostController){
    val navTo: (NAV_ROUTE) -> Unit={route->
        //특정 라우트로의 이동을 구현한다
        navHostController.navigate(route.routeName)
    }
    //뒤로가기 이동처리
    val goBack: () -> Unit={
        navHostController.navigateUp()
    }
}


//1번
@Composable
fun NavigationGraph(startDestination:NAV_ROUTE = NAV_ROUTE.MAIN){
    //네비게이션 컨트롤러
    val navController = rememberNavController()

    //네비게이션 라우트 액션
    val routeAction = remember(navController){RouteAction(navController)}

    //네비게이션 결정
    //네비게이션 연결할 애들을 설정한다
    NavHost(navController,startDestination.routeName){
        //라우트이름 = 키
        composable(NAV_ROUTE.MAIN.routeName){
            //화면=값
            //routeAction을 remember로 가지고 있는 이유는 compose에서 데이터를 가지고있기 위함이다
            MainScreen(routeAction = routeAction)
        }
        composable(NAV_ROUTE.LOGIN.routeName){
            LoginScreen(routeAction = routeAction)
        }
        composable(NAV_ROUTE.REGISTER.routeName){
            MainScreen(routeAction = routeAction)
        }
        composable(NAV_ROUTE.USER_PROFILE.routeName){
            MainScreen(routeAction = routeAction)
        }
        composable(NAV_ROUTE.SETTING.routeName){
            MainScreen(routeAction = routeAction)
        }
    }
}

//더미 스크린들 만들기
@Composable
//routeaction가지는 이유 화면을 이동해야하기 때문에
fun MainScreen(routeAction:RouteAction){
    Surface(modifier = Modifier.fillMaxSize()){
        Column(Modifier.padding(16.dp)){
            NavButton(route = NAV_ROUTE.MAIN, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.LOGIN, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.REGISTER, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.USER_PROFILE, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.SETTING, routeAction = routeAction)
        }
    }
}

//로그인 화면
@Composable
fun LoginScreen(routeAction: RouteAction){
    Surface(Modifier.fillMaxSize()){
        Box(Modifier.padding(8.dp), Alignment.Center){
            Text("로그인 화면")
            Button(onClick = routeAction.goBack){

            }
        }
    }
}

//컬럼안에 있는 네비게이션 버튼
@Composable
fun ColumnScope.NavButton(route:NAV_ROUTE, routeAction:RouteAction){
    Button(onClick = {
        routeAction.navTo(route)
    }, colors = ButtonDefaults.buttonColors(containerColor = route.btnColor),
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .fillMaxSize()
        ){
        Text(text = route.description)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Navigation_lectureTheme {

    }
}