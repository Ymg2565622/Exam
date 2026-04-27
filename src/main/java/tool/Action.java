package tool;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ビジネスロジックを記述した後、最終的にフォワードするjspファイルの名前を返す
 * なお、パスについては各クラスファイルの階層からの参照になるため注意。
 * 
 * 例：scoremanager/XXXAction.java における return "xxx.jsp"は scoremanager/xxx.jsp へのフォワードである
 */
public abstract class Action {
	public abstract String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception;
}
