package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class MainGenerator {

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        // 整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
//        子包项目打开用parentfile，总包打开下面用projectpath
        // 输入路径
//        String inputPath = new File(projectPath, "yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        String inputPath = new File(parentFile, "yuzi-generator-demo-projects/acm-template").getAbsolutePath();

        String outputPath = projectPath;
        // 生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
        // 生成动态文件
//      String inputDynamicFilePath = projectPath + File.separator + "yuzi-generator-basic"+ File.separator +"src/main/resources/templates/MainTemplate.java.ftl";
//      总包打开的情况下需要加子包名，//        这里因为是总包路径打开的所以拼了一个路径，注意如果是子包打开，会出错
        String inputDynamicFilePath = projectPath +File.separator +"src/main/resources/templates/MainTemplate.java.ftl";

        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(mainTemplateConfig);
    }
}