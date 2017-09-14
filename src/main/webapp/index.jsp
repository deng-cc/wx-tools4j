<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
</head>

<body>
上传临时素材 - 图片
<form action="/wx/uploadTempImage.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" />
</form>
<br>
上传临时素材 - 语音
<form action="/wx/uploadTempVoice.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" />
</form>
<br>
上传临时素材 - 视频
<form action="/wx/uploadTempVideo.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" />
</form>
<br>
上传临时素材 - 缩略图
<form action="/wx/uploadTempThumb.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" />
</form>

</body>
</html>
