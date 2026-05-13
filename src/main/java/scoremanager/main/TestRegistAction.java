package scoremanager.main;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestRegistAction")
public class TestRegistAction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        //学生ID（複数）
        String[] studentIds = request.getParameterValues("studentId");

        //共通項目
        String subjectId = request.getParameter("subjectId");
        String count = request.getParameter("count");

        String errorMessage = null;

        try {

            //学生ごとにループ（設計書ポイント）
            for (String studentId : studentIds) {

                String scoreStr = request.getParameter("point_" + studentId);

                //空欄は登録しない
                if (scoreStr == null || scoreStr.isEmpty()) {
                    continue;
                }

                int score;

                try {
                    score = Integer.parseInt(scoreStr);
                } catch (NumberFormatException e) {
                    errorMessage = "点数は数値で入力してください";
                    break;
                }

                //0～100チェック
                if (score < 0 || score > 100) {
                    errorMessage = "点数は0～100で入力してください";
                    break;
                }

                //DB登録（仮）
                System.out.println(
                    "学生ID:" + studentId +
                    " 科目ID:" + subjectId +
                    " 回数:" + count +
                    " 点数:" + score
                );

                // TODO: DAOでDB登録
                // TestDAO dao = new TestDAO();
                // dao.insert(studentId, subjectId, count, score);
            }

        } catch (Exception e) {
            errorMessage = "処理中にエラーが発生しました";
        }

        //エラー処理
        if (errorMessage != null) {
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
                   .forward(request, response);
            return;
        }

        //成功 → 完了画面へ
        request.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
               .forward(request, response);
    }
}
