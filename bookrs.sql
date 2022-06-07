/*
 Navicat MySQL Data Transfer

 Source Server         : 新连接
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : bookrs

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 07/06/2022 10:33:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `isbn` varchar(13) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `year_of_publication` int(4) NULL DEFAULT NULL,
  `publisher` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `categorylevelone` int(11) NOT NULL,
  `categoryleveltwo` int(11) NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`isbn`) USING BTREE,
  INDEX `fk_book_category1`(`categorylevelone`) USING BTREE,
  INDEX `fk_book_category2`(`categoryleveltwo`) USING BTREE,
  CONSTRAINT `fk_book_category1` FOREIGN KEY (`categorylevelone`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_book_category2` FOREIGN KEY (`categoryleveltwo`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1111111111111', 'test', '测试', 2022, '机械工业出版社', '1', 6, 89, 'test.jpg');
INSERT INTO `book` VALUES ('9787111213826', 'Java编程思想 第4版', '（美）Bruce Eckel', 2007, '机械工业出版社', '《Java编程思想（第4版）》赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在BruceEckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），《Java编程思想（第4版）》都能逐步指导你轻松掌握。', 18, 246, 'Java编程思想.jpg');
INSERT INTO `book` VALUES ('9787111547426', 'Java核心技术 卷1 基础知识 原书第10版', '（美）凯 S.霍斯特曼', 2016, '机械工业出版社', 'Java领域有影响力和价值的著作之一，由拥有20多年教学与研究经验的资深Java技术专家撰写（获Jolt大奖），与《Java编程思想》齐名，10余年全球畅销不衰，广受好评。', 18, 246, 'Java核心技术卷Ⅰ.jpg');
INSERT INTO `book` VALUES ('9787111573319', 'Java核心技术 卷2 高级特性 原书第10版', '（美）凯 S.霍斯特曼', 2017, '机械工业出版社', '本书是Java领域有影响力和价值的著作之一，由拥有20多年教学与研究经验的Java技术专家撰写（获Jolt大奖），与《Java编程思想》齐名，10余年全球畅销不衰，广受好评。', 18, 246, 'Java核心技术卷Ⅱ.jpg');
INSERT INTO `book` VALUES ('9787111641247', '深入理解Java虚拟机', '周志明', 2019, '机械工业出版社', '全书一共分为五个部分：走近Java、自动内存管理、虚拟机执行子系统、程序编译与代码优化、高效并发。各个部分之间基本上是互相独立的，没有必然的前后依赖，读者可以从任何一个感兴趣的专题开始阅读，但是每个部分中的各个章节间则有先后顺序。同时，在前言部分列出了每章主要更新的内容，以便阅读过第2版的读者可以快速定位获取到新的知识。', 18, 246, '深入理解Java虚拟机.jpg');
INSERT INTO `book` VALUES ('9787115293800', '图灵程序设计丛书 算法 第4版', '罗伯特·塞奇威克', 2012, '人民邮电出版社', '本书作为算法领域经典的参考书，全面介绍了关于算法和数据结构的必备知识，并特别针对排序、搜索、图处理和字符串处理进行了论述……', 18, 246, '图灵程序设计丛书 算法 第4版.jpg');
INSERT INTO `book` VALUES ('9787115521644', 'C++ Primer Plus（第6版）中文版', '史蒂芬·普拉达', 2019, '人民邮电出版社', '本书针对C++初学者，从C语言基础知识开始介绍，然后在此基础上详细阐述C++新增的特性，因此不要求读者有较多C语言方面的背景知识。本书可作为高等院校C++课程的教材，也可供初学者自学C++时使用。本书是一本友好而易于使用的自学指南，适合用做编程课程的教材，也可供熟悉其他语言的开发人员参考，以更深入地理解C++语言的基本知识。', 18, 246, 'C++ Primer Plus.jpg');
INSERT INTO `book` VALUES ('9787115546081', 'Python编程：从入门到实践（第2版）', '（美）埃里克·马瑟斯', 2020, '人民邮电出版社', '本书是针对所有层次Python读者而作的Python入门书。全书分两部分：第一部分介绍用Python编程所必须了解的基本概念，包括强大的Python库和工具，以及列表、字典、if语句、类、文件与异常、代码测试等内容；第二部分将理论付诸实践，讲解如何开发三个项目，包括简单的2D游戏、利用数据生成交互式的信息图以及创建和定制简单的Web应用，并帮助读者解决常见编程问题和困惑。', 18, 246, 'Python编程：从入门到实践（第2版）.jpg');
INSERT INTO `book` VALUES ('9787229134747', '帝国的崛起 : 从普鲁士到德意志', '［英］约翰·马里奥特 、［英］格兰特·罗伯逊', 2021, '重庆出版', '厘清普鲁士与德意志之间的关系，而这种关系过去常常被我们误解！普奥双雄并峙，为什么是普鲁士统一德国？普鲁士与法、英、俄、奥等强国博弈，如何影响500年来欧洲格局深刻变化！普鲁士生死存亡之际，腓特烈大帝、俾斯麦等强人是如何扭转国家命运的？在崛起的过程中，普鲁士曾经历了四次衰退期，还一度面临亡国的危险，但在历史紧要关头，大选帝侯、腓特烈大帝、施泰因和沙尔霍斯特、俾斯麦等伟人横空出世。他们是如何力挽狂澜、扭转国运的？', 11, 145, '帝国的崛起 从普鲁士到德意志.jpg');
INSERT INTO `book` VALUES ('9787505751354', '大英帝国3000年', '（英）杰里米·布莱克', 2021, '中国友谊出版公司', '这是一本展现了英国历史全貌的精彩著作。杰里米·布莱克将3000年的大不列颠浓缩在此书中，带读者进行了一次令人叹为观止的英国历史之行。本书以年代为轴，从战争、政权更迭、经济和文化发展等方面，介绍了英国是如何从一个小小的岛屿国家逐渐壮大并一步步成为\"执世界之权柄\"数百年的大英帝国，又是如何逐渐式微的。', 11, 145, '大英帝国3000年.jpg');
INSERT INTO `book` VALUES ('9787508689845', '奥斯曼帝国六百年', '（英）帕特里克·贝尔福', 2018, '中信出版社', '奥斯曼帝国六百年，是三百年的强盛加上三百年的衰落的故事。它是一个地跨欧亚非的庞大帝国、世界的十字路口，国运兴衰牵动了世界历史的走向。土耳其人的祖先来自中亚大草原。公元1300年左右，他们迁徙到亚洲的*西端，在此建立了自己的国家。土耳其人拥有草原民族一贯的凌厉作风，而他们的灵活与包容则在那个时代独树一帜。仅仅经过三位开国苏丹的励精图治，土耳其人就以“帝国”自立，在欧洲留下战无不胜的威名。', 11, 143, '奥斯曼帝国六百年.jpg');
INSERT INTO `book` VALUES ('9787509809501', '中国共产党历史 第二卷 （1949-1978）', '中共中央党史研究室', 2011, '中共党史出版社', '《中国共产党历史》第二卷，是继《中国共产党历史》第一卷出版以来的又一部中国共产党历史基本著作，反映的是中国共产党1949年—1978年的历史。这是一段波澜起伏的历史，是中国共产党带领全国人民艰辛探索中国自己的社会主义道路的历史。该书的编修，始终以历史决议为指导，以新时期党历次代表大会和中央全会有关党的历史的论述为准绳，并在撰写中得到中央领导同志亲切关怀和精心指导，若干重要问题的表述均经中央批准，代表了目前对这29年历史研究的最高水准。', 4, 74, '中国共产党历史 第二卷 （1949-1978）.jpg');
INSERT INTO `book` VALUES ('9787509809815', '中国共产党历史 第一卷 （1921-1949）', '中共中央党史研究室', 2011, '中共党史出版社', '《中国共产党历史·第1卷（1921—1949）（套装上下册）》包括了：鸦片战争后的中国社会和国际环境、鸦片战争与近代中国社会的演变、辛亥革命及其后的中围政治、民族资本主义的发展和无产阶级队伍的壮大、新文化运动的兴起、二十世纪初的国际环境等内容。', 4, 74, '中国共产党历史 第一卷 （1921-1949）.jpg');
INSERT INTO `book` VALUES ('9787513924894', '战争论（全三册）', '（德）卡尔·冯·克劳塞维茨', 2020, '民主与建设出版', '《战争论》是西方近代军事理论的集大成之作。作者克劳塞维茨通过对1566年至1815年间130多个战例的复盘分析，并结合自己亲历的几次战争的经验，对军事与政治、战争与媾和、进攻与防御、战略与战术、物质力量与精神力量、常备军战争与人民战争等问题进行了深刻思考，终完成了这部经典之作。', 5, 86, '战争论（全三册）.jpg');
INSERT INTO `book` VALUES ('9787514601305', '菊与刀', '（美）本尼迪克特', 2011, '中国画报出版社', '本书重新建构出日本文化以及日本战后重建的期许，书中，她不但以文化形貌论谈论了日本文化的特质，还从孩子教养的角度剖析日本人的生命史。', 11, 143, '菊与刀.jpg');
INSERT INTO `book` VALUES ('9787516913291', '周易', '姬昌', 2020, '华龄出版社', '《周易》是一部中国古典哲学书籍，是建立在阴阳二元论基础上对事物运行规律加以论证和描述的书籍，其内容宏大广博，无所不备，既有天道规律和地道法则，也有人道准则。', 2, 44, '周易.jpg');
INSERT INTO `book` VALUES ('9787521736816', '枪炮、病菌与钢铁', '（美）贾雷德·戴蒙德', 2022, '中信出版社', ' 文明的先发与落后，社会的发展与倒退，一直是人类社会关注的大议题。就此，西方社会普遍认为西方国家先进的技术、完善的社会阶层、百花齐放的文化成果，共同造就了西方文明优于其他，并决定了西方对世界的统治地位，甚至认为究其根本是种族的优越性决定他们的必然成就。《枪炮、病菌与钢铁》告诉我们，答案并非如此。环境、地理因素才是决定历史的天平向西方倾斜的主因。', 11, 140, '枪炮、病菌与钢铁.jpg');
INSERT INTO `book` VALUES ('9787532776771', '挪威的森林', '（日）村上春树', 2018, '上海译文出版社', '《挪威的森林》是日本作家村上春树所著的一部长篇爱情小说，影响了几代读者的青春名作。故事讲述主角渡边纠缠在情绪不稳定且患有精神疾病的直子和开朗活泼的小林绿子之间，苦闷彷徨，最终展开了自我救赎和成长的旅程。彻头彻尾的现实笔法，描绘了逝去的青春风景，小说中弥散着特有的感伤和孤独气氛。自1987年在日本问世后，该小说在年轻人中引起共鸣，风靡不息。', 9, 127, '挪威的森林.jpg');
INSERT INTO `book` VALUES ('9787536692930', '三体', '刘慈欣', 2008, '重庆出版社', '文化大革命如火如茶进行的同时，南方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。', 9, 126, '三体.jpg');
INSERT INTO `book` VALUES ('9787544253994', '百年孤独', '（哥伦比亚）加西亚·马尔克斯', 2011, '南海出版社', '《百年孤独》是魔幻现实主义文学的代表作，描写了布恩迪亚家族七代人的传奇故事，以及加勒比海沿岸小镇马孔多的百年兴衰，反映了拉丁美洲一个世纪以来风云变幻的历史。作品融入神话传说、民间故事、宗教典故等神秘因素，巧妙地糅合了现实与虚幻，展现出一个瑰丽的想象世界，成为20世界最重要的经典文学巨著之一。', 9, 127, '百年孤独.jpg');
INSERT INTO `book` VALUES ('9787544277617', '霍乱时期的爱情', '（哥伦比亚）加西亚·马尔克斯', 2015, '南海出版社', '不可避免，苦杏仁的气味总是让他想起爱情受阻后的命运。刚一走进还处在昏暗之中的房间，胡维纳尔·乌尔比诺医生就察觉出这种味道……', 9, 127, '霍乱时期的爱情.jpg');
INSERT INTO `book` VALUES ('9787557557843', '哆啦A梦珍藏版', '藤子.F.不二雄', 2020, '吉林美术出版社', '小学馆出版的瓢虫系列哆啦A梦一共出了45卷，为了纪念哆啦A梦50周年推出了一本第0卷，收录了藤本老师在各年龄段杂志上连载的第一话故事，分6个版本讲述哆啦A梦是如何来到野比大雄家的。', 10, 130, '哆啦A梦珍藏版.jpg');
INSERT INTO `book` VALUES ('9787559437358', '鲁滨逊漂流记', '（英）丹尼尔·笛福', 2019, '江苏凤凰文艺出版社', '本书是一部回忆录式冒险小说，讲述了鲁滨逊在孤岛生活了28年并重返文明社会的故事。第一部分写鲁滨逊离家三次航海的经历，在巴西买了种植园；第二部分描写了鲁滨逊在一座荒无人烟的海岛上度过了28年孤独的时光；第三部分叙述他从荒岛回来后的事情，主要描写了他由陆路从葡萄牙回英国途中遭遇狼群的经历。', 9, 127, '鲁滨逊漂流记.jpg');
INSERT INTO `book` VALUES ('9787559449337', '骆驼祥子', '老舍', 2020, '江苏凤凰文艺出版社', '讲述的是中国北平城里的一个年轻好强、充满生命活力的人力车夫祥子三起三落的人生经历。从农村来到城市的祥子，渴望以自己的诚实劳动买一辆属于自己的车。做个独立的劳动者是祥子的志愿、希望，凭着勤劳和坚忍，他用三年的时间省吃俭用，终于实现了理想，成为自食其力的上等车夫。但刚拉半年，车就在兵荒马乱中被逃兵掳走，祥子失去了洋车，只牵回三匹骆驼。祥子没有灰心，他依然倔强地从头开始，更加克己地拉车攒钱……', 9, 126, '骆驼祥子.jpg');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT 0,
  `type` int(2) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 280 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '马克思主义、列宁主义、毛泽东思想、邓小平理论', 0, 1);
INSERT INTO `category` VALUES (2, '哲学、宗教', 0, 1);
INSERT INTO `category` VALUES (3, '社会科学总论', 0, 1);
INSERT INTO `category` VALUES (4, '政治、法律', 0, 1);
INSERT INTO `category` VALUES (5, '军事', 0, 1);
INSERT INTO `category` VALUES (6, '经济', 0, 1);
INSERT INTO `category` VALUES (7, '文化、科学、教育、体育', 0, 1);
INSERT INTO `category` VALUES (8, '语言、文字', 0, 1);
INSERT INTO `category` VALUES (9, '文学', 0, 1);
INSERT INTO `category` VALUES (10, '艺术', 0, 1);
INSERT INTO `category` VALUES (11, '历史、地理', 0, 1);
INSERT INTO `category` VALUES (12, '自然科学总论', 0, 1);
INSERT INTO `category` VALUES (13, '数理科学和化学', 0, 1);
INSERT INTO `category` VALUES (14, '天文学、地球科学', 0, 1);
INSERT INTO `category` VALUES (15, '生物科学', 0, 1);
INSERT INTO `category` VALUES (16, '医药、卫生', 0, 1);
INSERT INTO `category` VALUES (17, '农业科学', 0, 1);
INSERT INTO `category` VALUES (18, '工业技术', 0, 1);
INSERT INTO `category` VALUES (19, '交通运输', 0, 1);
INSERT INTO `category` VALUES (20, '航空、航天', 0, 1);
INSERT INTO `category` VALUES (21, '环境科学、安全科学', 0, 1);
INSERT INTO `category` VALUES (22, '综合性图书', 0, 1);
INSERT INTO `category` VALUES (23, '马克思、恩格斯著作', 1, 2);
INSERT INTO `category` VALUES (24, '列宁著作', 1, 2);
INSERT INTO `category` VALUES (25, '斯大林著作', 1, 2);
INSERT INTO `category` VALUES (26, '毛泽东著作', 1, 2);
INSERT INTO `category` VALUES (27, '邓小平著作', 1, 2);
INSERT INTO `category` VALUES (28, '马克思、恩格斯、列宁、斯大林、毛泽东、邓小平著作汇编\r\n', 1, 2);
INSERT INTO `category` VALUES (29, '马克思、恩格斯、列宁、斯大林、毛泽东、邓小平生平和传记', 1, 2);
INSERT INTO `category` VALUES (30, '马克思主义、列宁主义、毛泽东思想、邓小平理论的学习和研究', 1, 2);
INSERT INTO `category` VALUES (31, '哲学教育与普及', 2, 2);
INSERT INTO `category` VALUES (32, '哲学理论', 2, 2);
INSERT INTO `category` VALUES (33, '世界哲学', 2, 2);
INSERT INTO `category` VALUES (44, '中国哲学', 2, 2);
INSERT INTO `category` VALUES (45, '亚洲哲学', 2, 2);
INSERT INTO `category` VALUES (46, '非洲哲学', 2, 2);
INSERT INTO `category` VALUES (47, '欧洲哲学', 2, 2);
INSERT INTO `category` VALUES (48, '大洋洲哲学', 2, 2);
INSERT INTO `category` VALUES (49, '美洲哲学', 2, 2);
INSERT INTO `category` VALUES (50, '思维科学', 2, 2);
INSERT INTO `category` VALUES (51, '逻辑学（论理学）', 2, 2);
INSERT INTO `category` VALUES (52, '伦理学（道德哲学）', 2, 2);
INSERT INTO `category` VALUES (53, '美学', 2, 2);
INSERT INTO `category` VALUES (54, '心理学', 2, 2);
INSERT INTO `category` VALUES (55, '宗教', 2, 2);
INSERT INTO `category` VALUES (56, '社会科学理论与方法论', 3, 2);
INSERT INTO `category` VALUES (57, '社会科学现状及发展', 3, 2);
INSERT INTO `category` VALUES (58, '社会科学机构、团体、会议', 3, 2);
INSERT INTO `category` VALUES (59, '社会科学研究方法', 3, 2);
INSERT INTO `category` VALUES (60, '社会科学教育与普及', 3, 2);
INSERT INTO `category` VALUES (61, '社会科学丛书、文集、连续性出版物', 3, 2);
INSERT INTO `category` VALUES (62, '社会科学参考工具书', 3, 2);
INSERT INTO `category` VALUES (63, '社会科学文献检索工具书', 3, 2);
INSERT INTO `category` VALUES (64, '统计学', 3, 2);
INSERT INTO `category` VALUES (65, '社会学', 3, 2);
INSERT INTO `category` VALUES (66, '人口学', 3, 2);
INSERT INTO `category` VALUES (67, '管理学', 3, 2);
INSERT INTO `category` VALUES (68, '系统科学', 3, 2);
INSERT INTO `category` VALUES (69, '民族学', 3, 2);
INSERT INTO `category` VALUES (70, '人才学', 3, 2);
INSERT INTO `category` VALUES (71, '劳动科学', 3, 2);
INSERT INTO `category` VALUES (72, '政治理论', 4, 2);
INSERT INTO `category` VALUES (73, '国际共产主义运动', 4, 2);
INSERT INTO `category` VALUES (74, '中国共产党', 4, 2);
INSERT INTO `category` VALUES (75, '各国共产党', 4, 2);
INSERT INTO `category` VALUES (76, '工人、农民、青年、妇女运动与组织', 4, 2);
INSERT INTO `category` VALUES (77, '世界政治', 4, 2);
INSERT INTO `category` VALUES (78, '中国政治', 4, 2);
INSERT INTO `category` VALUES (79, '各国政治', 4, 2);
INSERT INTO `category` VALUES (80, '外交、国际关系', 4, 2);
INSERT INTO `category` VALUES (81, '法律', 4, 2);
INSERT INTO `category` VALUES (82, '军事理论', 5, 2);
INSERT INTO `category` VALUES (83, '世界军事', 5, 2);
INSERT INTO `category` VALUES (84, '中国军事', 5, 2);
INSERT INTO `category` VALUES (85, '各国军事', 5, 2);
INSERT INTO `category` VALUES (86, '战略学、战役学、战术学', 5, 2);
INSERT INTO `category` VALUES (87, '军事技术', 5, 2);
INSERT INTO `category` VALUES (88, '军事地形学、军事地理学', 5, 2);
INSERT INTO `category` VALUES (89, '经济学', 6, 2);
INSERT INTO `category` VALUES (90, '世界各国经济概况、经济史、经济地理', 6, 2);
INSERT INTO `category` VALUES (91, '经济计划与管理', 6, 2);
INSERT INTO `category` VALUES (92, '农业经济', 6, 2);
INSERT INTO `category` VALUES (93, '工业经济', 6, 2);
INSERT INTO `category` VALUES (94, '信息产业经济（总论）', 6, 2);
INSERT INTO `category` VALUES (95, '交通运输经济', 6, 2);
INSERT INTO `category` VALUES (96, '旅游经济', 6, 2);
INSERT INTO `category` VALUES (97, '邮电经济', 6, 2);
INSERT INTO `category` VALUES (98, '贸易经济', 6, 2);
INSERT INTO `category` VALUES (99, '财经、金融', 6, 2);
INSERT INTO `category` VALUES (100, '文化理论', 7, 2);
INSERT INTO `category` VALUES (101, '世界各国文化与文化事业', 7, 2);
INSERT INTO `category` VALUES (102, '信息与知识传播', 7, 2);
INSERT INTO `category` VALUES (103, '科学、科学研究', 7, 2);
INSERT INTO `category` VALUES (104, '教育', 7, 2);
INSERT INTO `category` VALUES (105, '体育', 7, 2);
INSERT INTO `category` VALUES (106, '语言学', 8, 2);
INSERT INTO `category` VALUES (107, '汉语', 8, 2);
INSERT INTO `category` VALUES (108, '中国少数民族语言', 8, 2);
INSERT INTO `category` VALUES (109, '常用外国语', 8, 2);
INSERT INTO `category` VALUES (110, '汉藏语系', 8, 2);
INSERT INTO `category` VALUES (111, '阿尔泰语系（突厥-蒙古-通古斯语系）', 8, 2);
INSERT INTO `category` VALUES (112, '南亚语系（奥斯特罗-亚细亚语系）', 8, 2);
INSERT INTO `category` VALUES (113, '南印语系（达罗毗荼语系、德拉维达语系）', 8, 2);
INSERT INTO `category` VALUES (114, '南岛语系（马来亚-波里尼西亚语系）', 8, 2);
INSERT INTO `category` VALUES (115, '东北亚诸语言', 8, 2);
INSERT INTO `category` VALUES (116, '高加索语系（伊比利亚-高加索语系）', 8, 2);
INSERT INTO `category` VALUES (117, '乌拉尔语系（芬兰-乌戈尔语系）', 8, 2);
INSERT INTO `category` VALUES (118, '闪-含语系（阿非罗-亚细亚语系）', 8, 2);
INSERT INTO `category` VALUES (119, '印欧语系', 8, 2);
INSERT INTO `category` VALUES (120, '非洲诸语言', 8, 2);
INSERT INTO `category` VALUES (121, '美洲诸语言', 8, 2);
INSERT INTO `category` VALUES (122, '大洋洲诸语言', 8, 2);
INSERT INTO `category` VALUES (123, '国际辅助语', 8, 2);
INSERT INTO `category` VALUES (124, '文学理论', 9, 2);
INSERT INTO `category` VALUES (125, '世界文学', 9, 2);
INSERT INTO `category` VALUES (126, '中国文学', 9, 2);
INSERT INTO `category` VALUES (127, '各国文学', 9, 2);
INSERT INTO `category` VALUES (128, '艺术理论', 10, 2);
INSERT INTO `category` VALUES (129, '世界各国艺术概况', 10, 2);
INSERT INTO `category` VALUES (130, '绘画', 10, 2);
INSERT INTO `category` VALUES (131, '书法、篆刻', 10, 2);
INSERT INTO `category` VALUES (132, '雕塑', 10, 2);
INSERT INTO `category` VALUES (133, '摄影艺术', 10, 2);
INSERT INTO `category` VALUES (134, '工艺美术', 10, 2);
INSERT INTO `category` VALUES (135, '建筑艺术', 10, 2);
INSERT INTO `category` VALUES (136, '音乐', 10, 2);
INSERT INTO `category` VALUES (137, '舞蹈', 10, 2);
INSERT INTO `category` VALUES (138, '戏剧艺术', 10, 2);
INSERT INTO `category` VALUES (139, '电影、电视艺术', 10, 2);
INSERT INTO `category` VALUES (140, '史学理论', 11, 2);
INSERT INTO `category` VALUES (141, '世界史', 11, 2);
INSERT INTO `category` VALUES (142, '中国史', 11, 2);
INSERT INTO `category` VALUES (143, '亚洲史', 11, 2);
INSERT INTO `category` VALUES (144, '非洲史', 11, 2);
INSERT INTO `category` VALUES (145, '欧洲史', 11, 2);
INSERT INTO `category` VALUES (146, '大洋洲史', 11, 2);
INSERT INTO `category` VALUES (147, '美洲史', 11, 2);
INSERT INTO `category` VALUES (148, '传记', 11, 2);
INSERT INTO `category` VALUES (149, '文物考古', 11, 2);
INSERT INTO `category` VALUES (150, '风俗习惯', 11, 2);
INSERT INTO `category` VALUES (151, '地理', 11, 2);
INSERT INTO `category` VALUES (152, '自然科学理论与方法论', 12, 2);
INSERT INTO `category` VALUES (153, '自然科学现状及发展', 12, 2);
INSERT INTO `category` VALUES (154, '自然科学机构、团体、会议', 12, 2);
INSERT INTO `category` VALUES (155, '自然科学研究方法', 12, 2);
INSERT INTO `category` VALUES (156, '自然科学教育与普及', 12, 2);
INSERT INTO `category` VALUES (157, '自然科学丛书、文集、连续性出版物', 12, 2);
INSERT INTO `category` VALUES (158, '自然科学参考工具书', 12, 2);
INSERT INTO `category` VALUES (159, '自然科学文献检索工具', 12, 2);
INSERT INTO `category` VALUES (160, '自然科学调查、考察', 12, 2);
INSERT INTO `category` VALUES (161, '自然研究、自然历史', 12, 2);
INSERT INTO `category` VALUES (162, '非线性科学', 12, 2);
INSERT INTO `category` VALUES (163, '系统科学', 12, 2);
INSERT INTO `category` VALUES (164, '情报学、情报工作', 12, 2);
INSERT INTO `category` VALUES (165, '数学', 13, 2);
INSERT INTO `category` VALUES (166, '力学', 13, 2);
INSERT INTO `category` VALUES (167, '物理学', 13, 2);
INSERT INTO `category` VALUES (168, '化学', 13, 2);
INSERT INTO `category` VALUES (169, '晶体学', 13, 2);
INSERT INTO `category` VALUES (170, '天文学', 14, 2);
INSERT INTO `category` VALUES (171, '测绘学', 14, 2);
INSERT INTO `category` VALUES (172, '地球物理学', 14, 2);
INSERT INTO `category` VALUES (173, '大气科学（气象学）', 14, 2);
INSERT INTO `category` VALUES (174, '地质学', 14, 2);
INSERT INTO `category` VALUES (175, '海洋学', 14, 2);
INSERT INTO `category` VALUES (176, '自然地理学', 14, 2);
INSERT INTO `category` VALUES (177, '生物科学的理论与方法', 15, 2);
INSERT INTO `category` VALUES (178, '生物科学现状与发展', 15, 2);
INSERT INTO `category` VALUES (179, '生物科学的研究方法与技术', 15, 2);
INSERT INTO `category` VALUES (180, '生物科学教育与普及', 15, 2);
INSERT INTO `category` VALUES (181, '生物资源调查', 15, 2);
INSERT INTO `category` VALUES (182, '普通生物学', 15, 2);
INSERT INTO `category` VALUES (183, '细胞生物学', 15, 2);
INSERT INTO `category` VALUES (184, '遗传学', 15, 2);
INSERT INTO `category` VALUES (185, '生理学', 15, 2);
INSERT INTO `category` VALUES (186, '生物化学', 15, 2);
INSERT INTO `category` VALUES (187, '生物物理学', 15, 2);
INSERT INTO `category` VALUES (188, '分子生物学', 15, 2);
INSERT INTO `category` VALUES (189, '生物工程学（生物技术）', 15, 2);
INSERT INTO `category` VALUES (190, '环境生物学', 15, 2);
INSERT INTO `category` VALUES (191, '古生物学', 15, 2);
INSERT INTO `category` VALUES (192, '微生物学', 15, 2);
INSERT INTO `category` VALUES (193, '植物学', 15, 2);
INSERT INTO `category` VALUES (194, '动物学', 15, 2);
INSERT INTO `category` VALUES (195, '昆虫学', 15, 2);
INSERT INTO `category` VALUES (196, '人类学', 15, 2);
INSERT INTO `category` VALUES (197, '一般理论', 16, 2);
INSERT INTO `category` VALUES (198, '现状与发展', 16, 2);
INSERT INTO `category` VALUES (199, '医学研究方法', 16, 2);
INSERT INTO `category` VALUES (200, '预防医学、卫生学', 16, 2);
INSERT INTO `category` VALUES (201, '中国医学', 16, 2);
INSERT INTO `category` VALUES (202, '基础医学', 16, 2);
INSERT INTO `category` VALUES (203, '临床医学', 16, 2);
INSERT INTO `category` VALUES (204, '内科学', 16, 2);
INSERT INTO `category` VALUES (205, '外科学', 16, 2);
INSERT INTO `category` VALUES (206, '妇产科学', 16, 2);
INSERT INTO `category` VALUES (207, '儿科学', 16, 2);
INSERT INTO `category` VALUES (208, '肿瘤学', 16, 2);
INSERT INTO `category` VALUES (209, '神经病学与精神病学', 16, 2);
INSERT INTO `category` VALUES (210, '皮肤病学与性病学', 16, 2);
INSERT INTO `category` VALUES (211, '耳鼻咽喉科学', 16, 2);
INSERT INTO `category` VALUES (212, '眼科学', 16, 2);
INSERT INTO `category` VALUES (213, '口腔科学', 16, 2);
INSERT INTO `category` VALUES (214, '外国民族医学', 16, 2);
INSERT INTO `category` VALUES (215, '特种医学', 16, 2);
INSERT INTO `category` VALUES (216, '药学', 16, 2);
INSERT INTO `category` VALUES (217, '一般性理论', 17, 2);
INSERT INTO `category` VALUES (218, '农业科学技术现状与发展', 17, 2);
INSERT INTO `category` VALUES (219, '农业科学研究、试验', 17, 2);
INSERT INTO `category` VALUES (220, '农业经济', 17, 2);
INSERT INTO `category` VALUES (221, '农业基础科学', 17, 2);
INSERT INTO `category` VALUES (222, '农业工程', 17, 2);
INSERT INTO `category` VALUES (223, '农学（农艺学）', 17, 2);
INSERT INTO `category` VALUES (224, '植物保护', 17, 2);
INSERT INTO `category` VALUES (225, '农作物', 17, 2);
INSERT INTO `category` VALUES (226, '园艺', 17, 2);
INSERT INTO `category` VALUES (227, '林业', 17, 2);
INSERT INTO `category` VALUES (228, '畜牧、动物医学、狩猎、蚕、蜂', 17, 2);
INSERT INTO `category` VALUES (229, '水产、渔业', 17, 2);
INSERT INTO `category` VALUES (230, '工业技术理论', 18, 2);
INSERT INTO `category` VALUES (231, '工业技术现状与发展', 18, 2);
INSERT INTO `category` VALUES (232, '机构、团体、会议', 18, 2);
INSERT INTO `category` VALUES (233, '参考工具书', 18, 2);
INSERT INTO `category` VALUES (234, '工业经济', 18, 2);
INSERT INTO `category` VALUES (235, '一般工业技术', 18, 2);
INSERT INTO `category` VALUES (236, '矿业工程', 18, 2);
INSERT INTO `category` VALUES (237, '石油、天然气工业', 18, 2);
INSERT INTO `category` VALUES (238, '冶金工业', 18, 2);
INSERT INTO `category` VALUES (239, '金属学与金属工艺', 18, 2);
INSERT INTO `category` VALUES (240, '机械、仪表工业', 18, 2);
INSERT INTO `category` VALUES (241, '武器工业', 18, 2);
INSERT INTO `category` VALUES (242, '能源与动力工程', 18, 2);
INSERT INTO `category` VALUES (243, '原子能技术', 18, 2);
INSERT INTO `category` VALUES (244, '电工技术', 18, 2);
INSERT INTO `category` VALUES (245, '无线电电子学、电信技术', 18, 2);
INSERT INTO `category` VALUES (246, '自动化技术、计算机技术', 18, 2);
INSERT INTO `category` VALUES (247, '化学工业', 18, 2);
INSERT INTO `category` VALUES (248, '轻工业、手工业', 18, 2);
INSERT INTO `category` VALUES (249, '建筑科学', 18, 2);
INSERT INTO `category` VALUES (250, '水利工程', 18, 2);
INSERT INTO `category` VALUES (251, '交通运输经济', 19, 2);
INSERT INTO `category` VALUES (252, '综合运输', 19, 2);
INSERT INTO `category` VALUES (253, '铁路运输', 19, 2);
INSERT INTO `category` VALUES (254, '公路运输', 19, 2);
INSERT INTO `category` VALUES (255, '水路运输', 19, 2);
INSERT INTO `category` VALUES (256, '航空运输', 19, 2);
INSERT INTO `category` VALUES (257, '航空、航天技术的研究与探索', 20, 2);
INSERT INTO `category` VALUES (258, '航空', 20, 2);
INSERT INTO `category` VALUES (259, '航天（宇宙航行）', 20, 2);
INSERT INTO `category` VALUES (260, '航空、航天医学', 20, 2);
INSERT INTO `category` VALUES (261, '环境科学理论', 21, 2);
INSERT INTO `category` VALUES (262, '环境科学技术现状与发展', 21, 2);
INSERT INTO `category` VALUES (263, '环境保护宣传教育及普及', 21, 2);
INSERT INTO `category` VALUES (264, '环境保护参考工具书', 21, 2);
INSERT INTO `category` VALUES (265, '环境科学基础理论', 21, 2);
INSERT INTO `category` VALUES (266, '社会与环境', 21, 2);
INSERT INTO `category` VALUES (267, '环境保护管理', 21, 2);
INSERT INTO `category` VALUES (268, '灾害及其防治', 21, 2);
INSERT INTO `category` VALUES (269, '环境污染及其防治', 21, 2);
INSERT INTO `category` VALUES (270, '废物处理与综合利用', 21, 2);
INSERT INTO `category` VALUES (271, '环境质量评价与环境监测', 21, 2);
INSERT INTO `category` VALUES (272, '安全科学', 21, 2);
INSERT INTO `category` VALUES (273, '丛书', 22, 2);
INSERT INTO `category` VALUES (274, '百科全书、类书', 22, 2);
INSERT INTO `category` VALUES (275, '辞典', 22, 2);
INSERT INTO `category` VALUES (276, '论文集、全集、选集、杂著', 22, 2);
INSERT INTO `category` VALUES (277, '年鉴、年刊', 22, 2);
INSERT INTO `category` VALUES (278, '期刊、连续性出版物', 22, 2);
INSERT INTO `category` VALUES (279, '图书目录、文摘、索引', 22, 2);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `isbn` varchar(13) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_collect_user`(`user_id`) USING BTREE,
  INDEX `fk_collect_book`(`isbn`) USING BTREE,
  CONSTRAINT `fk_collect_book` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_collect_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (2, 14, '9787229134747');
INSERT INTO `collect` VALUES (3, 14, '9787505751354');
INSERT INTO `collect` VALUES (6, 14, '9787521736816');
INSERT INTO `collect` VALUES (10, 3, '9787557557843');
INSERT INTO `collect` VALUES (11, 22, '9787115521644');
INSERT INTO `collect` VALUES (12, 22, '9787115546081');
INSERT INTO `collect` VALUES (14, 24, '9787111213826');

-- ----------------------------
-- Table structure for rating
-- ----------------------------
DROP TABLE IF EXISTS `rating`;
CREATE TABLE `rating`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `isbn` varchar(13) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `rating` int(2) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_rating_user`(`user_id`) USING BTREE,
  INDEX `fk_rating_book`(`isbn`) USING BTREE,
  CONSTRAINT `fk_rating_book` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_rating_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rating
-- ----------------------------
INSERT INTO `rating` VALUES (1, 14, '9787111213826', 9, '很好的书。作为一个工作满一年两个月的初级搬砖工程师，读这本书花了1个月左右吧，理解了百分之30最多了，与其说有的地方看不懂，不如说没耐心(总是在地铁上看)。对于平时代码的写法，现在知道为什么那样写了。对于书中介绍的很多地方，我在代码里都没见过，也不知道其应用场景，也就觉得晦涩难懂了。最后补充一点，非科班出身，最好还是补点科班的基础，我也要补^_^', '2022-04-23 16:09:54', '2022-04-23 16:09:58');
INSERT INTO `rating` VALUES (2, 16, '9787532776771', 8, '不错', '2022-04-27 20:39:25', '2022-04-27 20:39:19');
INSERT INTO `rating` VALUES (3, 17, '9787532776771', 6, '一般', '2022-04-27 20:40:09', '2022-04-27 20:40:13');
INSERT INTO `rating` VALUES (4, 14, '9787521736816', 10, '内容很好', '2022-05-09 17:26:37', '2022-05-09 17:26:37');
INSERT INTO `rating` VALUES (5, 14, '9787229134747', 7, '关于普鲁士的介绍的很详细', '2022-05-09 16:20:58', '2022-05-09 16:20:58');
INSERT INTO `rating` VALUES (6, 14, '9787505751354', 8, '很不错的历史科普书。', '2022-05-09 16:21:44', '2022-05-09 16:21:44');
INSERT INTO `rating` VALUES (7, 14, '9787532776771', 1, '不好看', '2022-05-09 17:25:01', '2022-05-09 17:25:01');
INSERT INTO `rating` VALUES (8, 14, '9787115546081', 8, '学习Python的必备书', '2022-05-09 17:27:58', '2022-05-09 17:27:58');
INSERT INTO `rating` VALUES (11, 3, '9787557557843', 10, '童年回忆系列', '2022-05-10 14:51:54', '2022-05-10 14:51:54');
INSERT INTO `rating` VALUES (12, 20, '9787229134747', 8, '好看', '2022-05-10 22:30:10', '2022-05-10 22:30:10');
INSERT INTO `rating` VALUES (13, 20, '9787505751354', 10, '好看', '2022-05-10 22:30:19', '2022-05-10 22:30:19');
INSERT INTO `rating` VALUES (14, 20, '9787508689845', 9, '好看', '2022-05-10 22:30:30', '2022-05-10 22:30:30');
INSERT INTO `rating` VALUES (15, 20, '9787514601305', 9, '好看', '2022-05-10 22:30:40', '2022-05-10 22:30:40');
INSERT INTO `rating` VALUES (16, 21, '9787111213826', 9, '好看', '2022-05-10 22:31:27', '2022-05-10 22:31:27');
INSERT INTO `rating` VALUES (17, 21, '9787111547426', 10, '好看', '2022-05-10 22:31:37', '2022-05-10 22:31:37');
INSERT INTO `rating` VALUES (18, 21, '9787111573319', 10, '好看', '2022-05-10 22:31:43', '2022-05-10 22:31:43');
INSERT INTO `rating` VALUES (19, 21, '9787111641247', 8, '好看', '2022-05-10 22:31:55', '2022-05-10 22:31:55');
INSERT INTO `rating` VALUES (20, 21, '9787115521644', 10, '好看', '2022-05-10 22:32:03', '2022-05-10 22:32:03');
INSERT INTO `rating` VALUES (21, 21, '9787115546081', 4, '好看', '2022-05-10 22:32:12', '2022-05-10 22:32:12');
INSERT INTO `rating` VALUES (22, 21, '9787115293800', 6, '好看', '2022-05-10 22:32:23', '2022-05-10 22:32:23');
INSERT INTO `rating` VALUES (23, 22, '9787115521644', 8, '这本书是我在大二的那个暑假和大三第一个学期时候看的。现在回想起来，这本书看的时间真是长啊。当然这么长是有原因的，这本书有一个特点，每一章都有一些简单的课后题目。题目都是非常简单的，不过真的完成每一个题目需要的代码量也是非常大的。', '2022-05-15 15:22:49', '2022-05-15 15:22:49');
INSERT INTO `rating` VALUES (24, 22, '9787115546081', 10, '目前只把第一部分（Python语法部分）阅读完了，第二部分的项目后面有时间有兴趣再看，这书写的确实非常非常简单易懂，很适合入门新手。', '2022-05-15 15:23:43', '2022-05-15 15:23:43');
INSERT INTO `rating` VALUES (25, 2, '9787111213826', 9, '对Java从业者很有用的书，读后受益匪浅。', '2022-05-15 22:29:02', '2022-05-15 22:29:02');
INSERT INTO `rating` VALUES (29, 24, '9787115546081', 9, '很好的入门书！', '2022-05-23 21:55:22', '2022-05-23 21:55:22');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` int(2) NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `type` int(2) NOT NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '85989cd9a55f45da5c938b85b5a425583a1fe7090d385e9a', 1, '2000-07-07', '大连', '1911230111@qq.com', '13970421111', 'profile2.jpg', 1, '2022-04-18 23:04:53', '2022-06-07 10:32:14');
INSERT INTO `user` VALUES (2, 'test', 'f98955448240e4367ad42066d6fa37b1ad1bf23827a48f34', 0, '2022-04-18', '沈阳', '1911230111@qq.com', '13970421111', 'girl.jpg', 0, '2022-04-18 23:05:50', '2022-06-07 10:32:17');
INSERT INTO `user` VALUES (3, '李红', '12ba1624948ce9f187a3d70234235d64e075d01c2bd91035', 0, '2000-04-18', '哈尔滨', '1911230111@qq.com', '13970421111', 'girl.jpg', 0, '2022-04-18 15:14:04', '2022-06-07 10:32:18');
INSERT INTO `user` VALUES (4, '王明', 'b46a4f169740a2bb1b61c49b08695049b36aa5543682c88e', 1, '2022-04-12', '吉林', '1911230111@qq.com', '13970421111', 'akarin.jpg', 0, '2022-04-18 18:35:31', '2022-06-07 10:32:19');
INSERT INTO `user` VALUES (14, 'hpt', 'e81b2733944fa44f36043e2bf75215a4261353bc0fe88408', 1, '2022-04-17', '南昌', '1911230111@qq.com', '13970421111', 'profile1.jpg', 0, '2022-04-20 23:09:48', '2022-06-07 10:32:20');
INSERT INTO `user` VALUES (15, 'lhj', 'b71b9679fb98551f0ad68899f64c61875f9cd2ad7b15f06e', 1, NULL, '济南', '1911230111@qq.com', NULL, 'akarin.jpg', 0, '2022-04-26 22:45:54', '2022-06-07 10:31:47');
INSERT INTO `user` VALUES (16, 'fxc', 'e8b39ab81d18f61273458867e47a64446113889508341c07', 1, NULL, '成都', '1911230111@qq.com', NULL, 'akarin.jpg', 0, '2022-04-26 22:48:53', '2022-06-07 10:31:48');
INSERT INTO `user` VALUES (17, 'xxt', '577b1996085238f66213e32b37ec3d13559675c708793e04', 1, NULL, '重庆', '1911230111@qq.com', NULL, 'akarin.jpg', 0, '2022-04-26 22:51:18', '2022-06-07 10:31:50');
INSERT INTO `user` VALUES (20, '潘怡婷', '312b7a547a98965f53c15e6222739db6902f38e34ba0c881', 0, '2022-05-12', '南京', 'test@qq.com', '', 'akarin.jpg', 0, '2022-05-10 22:29:41', '2022-06-07 10:30:58');
INSERT INTO `user` VALUES (21, '郭冰宇', 'f98955448240e4367ad42066d6fa37b1ad1bf23827a48f34', 1, NULL, '北京', 'test@qq.com', NULL, 'akarin.jpg', 0, '2022-05-10 22:31:09', '2022-06-07 10:31:32');
INSERT INTO `user` VALUES (22, '白凯', '37846d53cf8942288d08e614956e4686b730c88e4184679d', 1, '2022-01-01', '上海', 'test@qq.com', '13970421111', 'akarin.jpg', 0, '2022-05-15 14:47:52', '2022-06-07 10:32:22');
INSERT INTO `user` VALUES (24, 'user', 'a3ba26052f4c65c99fd4a77b774480f03145e5fd9350f967', 0, NULL, NULL, NULL, NULL, 'akarin.jpg', 0, '2022-05-17 14:55:57', '2022-05-17 14:55:57');

SET FOREIGN_KEY_CHECKS = 1;
