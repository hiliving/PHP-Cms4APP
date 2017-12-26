<?php


//测试添加数据记录接口，为数据库添加记录
require_once('./addCommon.php');
class add extends addCommon{
    public function index(){
        $this->check();

        //增
        $sql = "insert into 
					movie(
					    `name`,
						`picUrl`,
						`descripe`,
						`orderid`,
						`time`,
						`type`
				    )
					values(
						'".$this->params['name']."',
						'".$this->params['picUrl']."',
						'".$this->params['descripe']."',
						'".$this->params['orderid']."',
						'".$this->params['time']."',
						'".$this->params['type']."'
					)";
        $connect = Db::getInstance()->connect();
        if(mysql_query($sql, $connect)) {
            return Response::show(200, '信息插入成功');
        } else {
            return Response::show(400, '信息插入失败');
        }
    }
}
$add = new add();
$add->index();