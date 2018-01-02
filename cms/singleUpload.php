<?php

/**

* @Date: 2017-08-20 14:50:39

* @Last Modified time: 2017-08-20 22:47:07

*/
require_once('./php/cms/response.php');
header("Content-Type:text/html;charset=utf-8");
/**
 * 如果是web上传，可选择是否判断提交参数isset($_POST['submit'])，手机APP可以不做判断
 */
if (true) {

       

        //临时文件名

        //构建临时文件

        $filename = $_FILES['upload']['tmp_name'];

        //判断是否是HTTP POST上传的

        if (!is_uploaded_file($filename)) {

                die('非法上传');

        }
        // 判断是否有错误,error = 0 表示上传成功

        if ($_FILES['upload']['error']!=0) {

                die('上传出错,请重新上传');

        }
        // 判断文件大小 php最大是2M

        // 要求上传文件的大小小于2M

        // 2M转换为字节2*1024*1024

        $file_max_size = 122*1024*1024;

        if ($_FILES['upload']['size']>$file_max_size) {

                die('文件过大');
        }
        // 获取文件扩展名

        $index = strrpos($_FILES['upload']['name'],'.');

        $ext = substr($_FILES['upload']['name'],$index);

        // $ext = strchr($_FILES['upload']['name'],'.');

        // 判断文件类型 不能通过;

        //开启php_fileinfo.dll扩展

        $fs = finfo_open(FILEINFO_MIME_TYPE);

        //finfo_file(资源类型,获取是哪个文件的MIME,也就是临时文件)

        $mime = finfo_file($fs,$filename);

        // 允许上传的文件类型

        $arr_ext = ['image/gif','image/jpeg','image/jpg','image/png'];

        // 判断是否包括当前文件类型

       /* if (!in_array($mime,$arr_ext)) {

                die('上传的文件类型错误');
        }*/

        //构建临时文件

        //在最初已经进行了临时文件的构建

        //构建目标文件

        $path = './upload/';
		if(!is_dir($path)){
			 mkdir ( $path,0777,true);
		}
        $destination = $path.uniqid().$ext;

        if (!move_uploaded_file($filename,$destination)) {

                die('上传失败');

        }
		$path = 'http://192.168.85.236/'.substr($destination,2);
        
		//echo '文件上传成功，路径为：'.'http://192.168.85.236/'.substr($destination,2);

        //echo '<div><img src='.$destination.'></div>';
		$f = StripSlashes($path);
		$arr = array(
				'path'=>$f
			);
		return Response::show(200, '首页数据获取成功', $arr);

}else{
		/*return Response::show(400, '非法', $videos);*/
}