package GregTech.TopazBackend.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

public class TemplateInit {
    @Resource(name="jdbcTemplate")
    private JdbcTemplate template ;
   public JdbcTemplate getTemplate(){
       return this.template;
   }
}
