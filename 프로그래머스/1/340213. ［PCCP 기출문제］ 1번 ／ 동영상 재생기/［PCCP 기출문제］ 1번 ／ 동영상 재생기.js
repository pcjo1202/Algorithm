function solution(video_len, pos, op_start, op_end, commands) {
    var answer = '';
    
    const [vl_mm, vl_ss] = video_len.split(":").map(Number)
    const [pos_mm, pos_ss] = pos.split(":").map(Number)
    const [ops_mm, ops_ss] = op_start.split(":").map(Number)
    const [ope_mm, ope_ss] = op_end.split(":").map(Number)
    
    const video_len_n = vl_mm * 60 + vl_ss;
    let pos_n = pos_mm * 60 + pos_ss;
    const ops_n = ops_mm * 60 + ops_ss;
    const ope_n = ope_mm * 60 + ope_ss;
    
    commands.forEach((command)=>{
        // 오프닝 구간이라면
        if(ops_n <= pos_n && pos_n <= ope_n){
            pos_n = ope_n;
        }
        
        if(command === "next"){
            if(Math.abs(video_len_n - pos_n) < 10){
                pos_n = video_len_n;
            }else{
                pos_n += 10;
            }
        }else if(command === "prev"){
            if(pos_n < 10){
                pos_n = 0;
            }else{
                pos_n -= 10;
            }
        }
        
        // 오프닝 구간이라면
        if(ops_n <= pos_n && pos_n <= ope_n){
            pos_n = ope_n;
        }
    })
    
    const mm = Math.floor(pos_n / 60);
    const ss = pos_n % 60
    
    return `${mm < 10 ? String(mm).padStart(2, "0") : mm}:${ss < 10 ? String(ss).padStart(2,"0") : ss}`;
}