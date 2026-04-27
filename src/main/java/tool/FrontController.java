package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 各パスを変換して該当するアクションを実行するフロントコントローラ―。
 * アクションの戻り値であるjspファイルにフォワードする。
 * 
 * 例外（DBエラー等のシステムエラー）については、コンソールにログを出し、web上ではエラーページに遷移する
 */
@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {
	
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		try {
			String path = request.getServletPath().substring(1);
			String name = path.replace(".a", "A").replace('/', '.');
			Action action = (Action)Class.forName(name).
					getDeclaredConstructor().newInstance();
			String url = action.execute(request, response);
			request.getRequestDispatcher(url).
				forward(request, response);
		} catch (Exception e) {
	        e.printStackTrace();
	        request.getRequestDispatcher("/error.jsp")
	                .forward(request, response);
		}
	}
	
	public void doGet(
			HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		doPost(request, response);
	}
	
}