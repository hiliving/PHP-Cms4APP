<?php
/**
 * 处理接口公共业务
 * movie(
`name`,
`picUrl`,
`descripe`,
`orderid`,
`time`,
`type`
)
 */
require_once('./response.php');
require_once('./db.php');
class addCommon {
    public $params;
    public $app;
    public function check() {
        $this->params['name'] = $name = isset($_POST['name']) ? $_POST['name'] : '';
        $this->params['picUrl'] = $picUrl = isset($_POST['picUrl']) ? $_POST['picUrl'] : '';
        $this->params['descripe'] = $descripe = isset($_POST['descripe']) ? $_POST['descripe'] : '';
        $this->params['orderid'] = $orderid = isset($_POST['orderid']) ? $_POST['orderid'] : '';
        $this->params['time'] = $time = isset($_POST['time']) ? $_POST['time'] : '';
        $this->params['type'] = $type = isset($_POST['type']) ? $_POST['type'] : '';
        //检查参数合法性
        if(!is_numeric($orderid)) {
            return Response::show(401, '参数不合法');
        }
    }
}