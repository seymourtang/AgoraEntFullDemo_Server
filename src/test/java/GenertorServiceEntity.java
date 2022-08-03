import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import org.junit.Test;

import java.util.Collections;


/**
 * 自动生成实实体类、repository、业务类
 * 需要注意：
 * 1. 核实数据库连接信息、代码生成路径、包名、需要生成的表名
 * 2. 代码生成完成后将自动生成的controller删除，将mapper.xml文件移动到resources/mapper目录下
 * 3. 如果表名为复数，将生成的相关代码改为单数形式，需要修改类名、类加注解@TableName注明原始表名、service接口名、service实现类名、repository下的mapper接口名、repository.xml名
 * 4. 在生成的实体类的deleted_at字段上加@TableLogic注解实现自动逻辑删除及判断
 * 5. 将生成的service和serviceImpl文件移到对应功能的包下
 * 6. 完成后注释generateCode()方法上的//@Test注解，避免误生成代码
 */
public class GenertorServiceEntity {

    /** 作者. */
    private static final String AUTHOR = "";

    /** 数据库 .*/
    /* !!! TODO: Change this to your own redis config */
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final String DB_URL = "" ;
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";


    /** 代码生成输出目录. */
    private static final String OUTPUT_DIR = "./";
    /** 生成的包名 .*/
    private static final String PACKEGE_NAME = "com.md.service";

    @Test
    public void generateCode() {
        generateByTables(PACKEGE_NAME, "songs");
    }

    private void generateByTables(String packageName, String... tableNames) {

        FastAutoGenerator.create(DB_URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(OUTPUT_DIR+"/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(packageName) // 设置父包名
//                            .moduleName("model") // 设置父包模块名
                            .entity("model.entity")
                            .mapper("repository")
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, OUTPUT_DIR+"/src/main/java/com/md/service/repository"))
                            .pathInfo(Collections.singletonMap(OutputFile.xml, OUTPUT_DIR+"/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames) // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok()
                            .addTableFills(new Column("created_at", FieldFill.INSERT))
                            .addTableFills(new Column("updated_at", FieldFill.INSERT_UPDATE))
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                    ; // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
