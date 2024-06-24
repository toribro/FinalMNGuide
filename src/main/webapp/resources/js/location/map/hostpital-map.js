$(function(){
    let address=document.getElementById('address');


    let mapContainer = document.getElementById('hostpial_map'), // 지도를 표시할 div 
    mapOption = {
     center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
     level: 3 // 지도의 확대 레벨
    };  
    
    // 지도를 생성합니다    
    let map = new kakao.maps.Map(mapContainer, mapOption); 
    
    // 주소-좌표 변환 객체를 생성합니다
    let geocoder = new kakao.maps.services.Geocoder();
    
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(address.value , function(result, status) {
    
     // 정상적으로 검색이 완료됐으면 
     if (status ===  kakao.maps.services.Status.OK) {
    
         let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
         // 결과값으로 받은 위치를 마커로 표시합니다
        //  let marker = new kakao.maps.Marker({
        //      map: map,
        //      position: coords
        //  });

        //지도의 중심좌표를 접속위치로 변경
        map.setCenter(coords);

        let ps = new kakao.maps.services.Places();

        ps.keywordSearch('동물병원',function(data,status,pagination){
            if(status=== kakao.maps.services.Status.OK){
              
                let bounds =new kakao.maps.LatLngBounds();

                for(let i=0; i<data.length; i++){
                    displayMarker(data[i]);
                    bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
                }
                map.setBounds(bounds);
            }

        }, {location: coords});
        }
        else{
            alert('주소를 변환하는데 실패했습니다.');
        }
         // 인포윈도우로 장소에 대한 설명을 표시합니다
        //  let infowindow = new kakao.maps.InfoWindow({
        //     content: '병원위치'
        //  });
        //  infowindow.open(map, marker);
    
        //  // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        //  map.setCenter(coords); 
     
    });    


    function displayMarker(place){
      
        let marker=new kakao.maps.Marker({
            map:map,
            position:new kakao.maps.LatLng(place.y,place.x)
        });
     
        let infowindow = new kakao.maps.InfoWindow({zIndex:1});
        infowindow.setContent('<div style="padding:5px;font-size:12px; border-radius:50px;">' + place.place_name + '</div>')
        infowindow.open(map, marker);
        
        kakao.maps.event.addListener(marker,'click',function(){
           
            location.href="https://map.kakao.com/link/search/"+place.address_name
        })
    }

})

 




