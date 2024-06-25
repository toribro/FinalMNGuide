
//썸네일 선택
function onvideoThumnail(){
    const inputVideo=document.querySelector("#Thunmailfile");
  
    inputVideo.addEventListener("change",function(){
      const backgroundImg=document.querySelector("#Thumnail-background");
      let reader=new FileReader();
      let files=this.files;
  
      console.log("type:",files);
  
      if(files!=null&& files[0]!=null){
  
         
          reader.onload = (e) =>{
          backgroundImg.style.backgroundImage
          =`url(${e.target.result})`
         }
      }
      
      reader.readAsDataURL(files[0])
  
     })
  
  
  }
  
  //video선택
  function onVideo(){
    const videoFile=document.querySelector("#videoInput");
    const video=document.querySelector("#video");
    //const thumnail=document.querySelector("#Thunmailfile");
    const thumnailBackground=document.querySelector("#Thumnail-background");
  
    videoFile.addEventListener("change",function(){

      let reader=new FileReader();
      const file = videoFile.files[0];
      const videourl = URL.createObjectURL(file);
      video.setAttribute("src", videourl);
    //   reader.onload = (e) =>{
    //     thumnailBackground.style.backgroundImage
    //     =`url(${e.target.result})`
    //   }
      video.play();
     })
  
  }