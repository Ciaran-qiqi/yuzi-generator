package com.yupi.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {

    private static volatile Meta meta;

    private MetaManager() {
        // 私有构造函数，防止外部实例化
    }

    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        Meta.FileConfig fileConfig = newMeta.getFileConfig();
        // todo 校验和处理默认值
        return newMeta;
    }
}

//解释一下上述代码：一种设计模式 —— 单例模式。保证项目运行期间只有一个 Meta 对象被创建，并且能够轻松获取。
//        1）定义了 meta 属性，用于接受 JSON 配置。使用 volatile 关键字修饰，确保多线程环境下的可见性。
//        2）添加了一个私有构造函数，防止外部用 new 的方式创建出多个对象，破坏单例
//        3）定义了 getMetaObject 方法，用于获取 meta 对象。如果是首次获取，则执行 initMeta 方法来初始化 meta 对象；否则直接获取已有对象。
//        此处使用 双检锁 进行并发控制，既保证了对象获取性能不会被锁影响，也能防止重复实例化。
//        4）定义了 initMeta 方法，用于从 JSON 文件中获取对象属性并初始化 meta 对象。
//        当然后续还可以执行对象校验、填充默认值等操作。
//
//        之后获取 meta 对象信息，只需要调用 MetaManager.getMetaObject 方法即可，
//        相比每次都获取 JSON 文件并解析，提高了性能并简化代码。