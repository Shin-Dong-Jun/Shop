window.addEventListener("load", function() {
    let posY = 0; // pan의 Y좌표값
    let isScroll = false;
    const panElement = document.querySelector(".pan");
    const naviElements = document.querySelectorAll(".navi > div");

    // 마우스 휠 이벤트 처리
    panElement.addEventListener("wheel", function(e) {
        if (isScroll) {
            return;
        }
        isScroll = true;

        const delta = e.deltaY;  // 휠의 방향
        if (delta > 0) {
            posY -= 100;
        } else if (delta < 0) {
            posY += 100;
        }

        // 범위 제한
        if (posY < -500) {
            posY = -500;
        }
        if (posY > 0) {
            posY = 0;
        }

        // 애니메이션 처리
        panElement.style.transition = "top 1s";
        panElement.style.top = posY + "%";

        setTimeout(function() {
            isScroll = false;
        }, 1000); // 1초 후에 스크롤 잠금 해제

        let number = (posY - 100) / -100;
        dotAni(number);
    });

    // 네비 클릭 시 해당 위치로 이동하는 기능
    naviElements.forEach(function(naviElement) {
        naviElement.addEventListener("click", function() {
            const data = naviElement.id;
            const number = data.charAt(data.length - 1);
            posY = (number - 1) * -100;
            isScroll = true;

            // 애니메이션 처리
            panElement.style.transition = "top 1s";
            panElement.style.top = posY + "%";

            setTimeout(function() {
                isScroll = false;
            }, 1000); // 1초 후에 스크롤 잠금 해제

            dotAni(number);
        });
    });

    // 네비게이션 깜빡임 처리
    function dotAni(number) {
        naviElements.forEach(function(naviElement) {
            naviElement.classList.remove("selected");
        });
        naviElements[number - 1].classList.add("selected");
    }
});