package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Doc;
import GregTech.TopazBackend.metadata.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("templateDao")
public class TemplateDao {
    private final JdbcTemplate jdbc;
    private final DocDao docDao;
    private static final Logger log = LoggerFactory.getLogger(TemplateDao.class);

    @Autowired
    public TemplateDao(JdbcTemplate jdbc, DocDao docDao) {
        this.jdbc = jdbc;
        this.docDao = docDao;
    }

    public static class templateMapper implements RowMapper<Template> {

        @Override
        public Template mapRow(ResultSet rs, int rowNum) throws SQLException {
            Template template = new Template();
            template.setContent(rs.getString("content"));
            template.setName(rs.getString("name"));
            template.setTemid(rs.getInt("temid"));
            template.setOwner(rs.getInt("owner"));
            return template;
        }


    }
    public List<Template> getTemplates(int uid){
        String sql ="select  * from  template where  owner=?";
        try {
           return jdbc.query(sql,new templateMapper(),uid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean templateFile(int did, int owner) {
        Doc doc = docDao.getDocByDid(did);
        if (doc == null) {
            return false;
        }
        String sql = "insert into template(owner, content, name) values (?,?,?) ";
        jdbc.update(sql, owner, doc.getContent(), doc.getName());
        return true;
    }

    public Template getTemplateBytemid(int temid) {
        try {
            String sql = "select * from template where temid=?";
            return jdbc.queryForObject(sql, new templateMapper(), temid);

        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean renameTemplate(int user, int temid, String name) {
        String sql = "update  template set name =? where temid=? and owner=?";
        try {
            int i = jdbc.update(sql, name, temid, user);
            if (i == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTemplate(int user, int temid) {
        String sql = "delete  from  template where  owner= ? and temid =?";
        try {
            jdbc.update(sql, user, temid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
