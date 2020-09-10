package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Blog;
import utils.IDutils;
import utils.MybatisUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class MyTest {

    @Test
    public void test() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IDutils.getId());
        blog.setTitle("Mybatis");
        blog.setAuthor("Sugar");
        blog.setCreateTime(new Date());
        blog.setViews(9999);

        mapper.addBlog(blog);

        blog.setId(IDutils.getId());
        blog.setTitle("Java");
        mapper.addBlog(blog);

        blog.setId(IDutils.getId());
        blog.setTitle("Spring");
        mapper.addBlog(blog);

        blog.setId(IDutils.getId());
        blog.setTitle("微服务");
        mapper.addBlog(blog);

        sqlSession.close();
    }

    @Test
    public void queryBlogIF() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap<>();
        map.put("title", "Python");
        map.put("id", "17b89e7e12994db6ad5806c1d470c34a");
        mapper.updateBlog(map);


        sqlSession.close();
    }

}
