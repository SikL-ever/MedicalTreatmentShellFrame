package com.dingtao.common.bean.homepage;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/17 15:01
 * @Description：描述信息
 */
public class ZhuBean {

    /**
     * benefitTaboo :  1．让病人了解颈椎病的有关知识，提高防病意识，增强治疗信心，掌握康复的方法。同时注意调节心理情绪，保持心理健康。 2．正确有效牵引，解除机械性压迫。 3．正确应用理疗、按摩、药物等综合治疗，以解除病痛。 4．正确指导病人的头颈功能锻炼，坚持颈部的活动锻炼，指导病人两手做捏橡皮球或手巾的训练，以及手指的各种动作。 5．非手术治疗过程中注意疼痛部位，肢体麻木无力的变化。按时测量体温、脉搏、呼吸、血压。 6．长期卧床的病人，应注意有关卧床并发症的预防与观察。 7．经常用50%的红花酒精按摩病人的骨突部，按摩上、下肢肌肉，鼓励病人主动加强各关节活动。 8．宜进食滋养筋脉，充益气血的食物，过分油腻及煎炸类食品，不宜进食。宜服食偏温必的蔬菜水果，如韭菜、香菜、胡萝卜、山药、桃子、葡萄、杏仁、核桃仁等。 9．避免受凉及不适当的颈中活动，夜眠时不宜用高枕，应用低枕枕于颈部，而枕头两侧略高，使侧卧时有保持颈部正直 10．药枕芳香，流通气血，宜采用。在平素工作中作适当的颈部活动，避免长时间低头劳作。
     * chineseMedicineTreatment :  1．神经根型用牛蒡、白蒺藜、僵蚕、鹿含草、丹参、川芎、白芍、秦艽、羌活等煎服，或口服华佗再造丸、蜈蚣粉。 2．脊髓型用熟地、萸肉、山药、紫河车、黄芪、鹿角、丹参、川芎、白芍等煎服，或口服全鹿丸。 3．交感型可用当归、麦冬、黄连、肉桂、炙甘草、石决明、天麻、珍珠母等煎服。 4．椎动脉型用天麻，白蒺藜、钩藤、半夏、白术、鹿含草、白芍等煎服。 5．症状缓解后可服六味地黄丸合大活络丸，以资巩固，减少复发。
     * createTime : 1547108511000
     * diseaseCategoryId : 17
     * id : 17
     * pathology : 颈椎病又称“颈椎综合征”，本病是由于颈椎增生，从而直接或间接刺激或压迫颈神经根、颈部脊髓椎动脉或交感神经所出现的一系列综合征候群。导致本病的原因可归纳为两种：一种是外部原因，即各种急慢性损伤造成；另一种是内部原因，即颈椎本身的退变，颈椎的椎间盘从30岁开始退变。
     * symptom : 神经根型颈椎病：颈项部疼痛、不适、活动受限。上颈椎病变，颈椎疼痛向枕部放射，枕部皮肤麻木；下颈椎病变，颈肩部疼痛并可向前臂圹射，一侧或双侧的上肢麻木，肌力减退严重者可有上肢的肌肉萎缩等。 脊髓型颈椎病：颈部不适，肌力减退，上、下肢麻木无力，行走困难，握物乏力，严重者导致截瘫。膝、踝反射亢进，病理反射阳性。 交感神经型颈椎病：头痛、视物模糊、听力减退、手足发热、心跳过速、汗出、面红等。 椎动脉型椎病：头颈活动到某一位置时，突然发生眩晕及下肢麻木无力而摔倒，意识往往清楚。
     * westernMedicineTreatment :  口服布洛芬、芬必得和硫酸软骨素。急性发生，疼痛剧烈时，可用地塞米松，待疼痛缓解后即应改用他药，强筋松或氯唑沙宗可缓解肌紧张而减痛，宜与止痛剂合同。
     */

    private String benefitTaboo;
    private String chineseMedicineTreatment;
    private long createTime;
    private int diseaseCategoryId;
    private int id;
    private String pathology;
    private String symptom;
    private String westernMedicineTreatment;

    public String getBenefitTaboo() {
        return benefitTaboo;
    }

    public void setBenefitTaboo(String benefitTaboo) {
        this.benefitTaboo = benefitTaboo;
    }

    public String getChineseMedicineTreatment() {
        return chineseMedicineTreatment;
    }

    public void setChineseMedicineTreatment(String chineseMedicineTreatment) {
        this.chineseMedicineTreatment = chineseMedicineTreatment;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getDiseaseCategoryId() {
        return diseaseCategoryId;
    }

    public void setDiseaseCategoryId(int diseaseCategoryId) {
        this.diseaseCategoryId = diseaseCategoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathology() {
        return pathology;
    }

    public void setPathology(String pathology) {
        this.pathology = pathology;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getWesternMedicineTreatment() {
        return westernMedicineTreatment;
    }

    public void setWesternMedicineTreatment(String westernMedicineTreatment) {
        this.westernMedicineTreatment = westernMedicineTreatment;
    }
}
