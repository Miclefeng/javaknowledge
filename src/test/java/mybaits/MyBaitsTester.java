package mybaits;

import com.javase.base.mybatis.entity.PassAuth;
import com.javase.base.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author miclefengzss
 * 2021/4/22 下午9:58
 */
public class MyBaitsTester {

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = null;

        try {
            sqlSession = MyBatisUtils.openSession();
            List<PassAuth> list = sqlSession.selectList("PassAuthMapper.selectAll");
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
}
