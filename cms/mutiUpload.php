<?php

/**

* @Date: 2017-08-20 19:55:33

* @Last Modified time: 2017-08-20 22:55:55

*/
require_once('./php/cms/response.php');
header("Content-Type:text/html;charset=utf-8");
/*isset($_POST['submit'])*/
if (true) {
        /*显示接收到的数据结构
                echo '<pre>';

                        print_r($_FILES);

                echo '</pre>';

                //三维数组转二维数组



                echo '<pre>';

                        print_r($arr);

                echo '</pre>';*/
          $arr = $_FILES['upload'];
         $arrs = array();
        foreach ($arr['tmp_name'] as $key => $value) {

                //构建临时文件

                $filename = $arr['tmp_name'][$key];

                //判断是否是post http方式上传的

                if (!is_uploaded_file($filename)) {

                        die('非法上传');

                }

                //判断上传是否有错,error = 0 表示上传成功

                if ($arr['error'][$key]!=0) {

                        die('上传出错,请重新上传');

                }

                //判断文件上传大小.大于22M不予上传

                $file_max_size = 22*1024*1024;

                if ($arr['size'][$key]>$file_max_size) {

                        die('文件过大');

                }

                //获取文件扩展名

                $ext = strchr($arr['name'][$key],'.');

                //开启php_fileinfo.dll扩展

                $fs = finfo_open(FILEINFO_MIME_TYPE);

                //finfo_file(资源类型,获取是那个文件的MIME,也就是临时文件)

                $mime = finfo_file($fs,$filename);

                //允许上传的文件类型

              /*    $arr_ext = ['image/jpeg','image/png'];*/

                  //判断是否包含当前文件类型

                /*  if (!in_array($mime,$arr_ext)) {

                          die('文件类型错误');

                  }*/

                  //构建临时文件

                  //构建目标文件

                  $path = './upload/';

                  $destination = $path.uniqid().$ext;

                  if (!move_uploaded_file($filename,$destination)) {

                          die('上传失败');

                  }

                     $paths = 'http://192.168.85.236/'.substr($destination,2);
                    array_push($arrs,$paths);
                /*
                 * 这两行是为了看web端的效果，APP访问时不需要显示
                  echo '<div><img src='.$destination.' alt=""></div>';

                 echo "<br/>";*/
        }
        $new = array(
            'path'=>$arrs
        );
         return Response::show(200, '上传成功',$new);
}

else{
    return Response::show(400, '上传失败',$new);
        echo '非法';

}

