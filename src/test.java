import java.util.LinkedList;
import java.util.List;

import com.hhhy.db.beans.Article;

class test {
	public static void main(String[] args){
		List<Article> importantArticle = new LinkedList<Article>();
		Article a = new Article("title","content","http://weibo.com/zxlady9218","website");
		a.setEmotion(0);
		a.setTime("time");
		importantArticle.add(a);
		for(Article r : importantArticle) {
			r.getUrl();
			r.getTitle();
		}
	}
}