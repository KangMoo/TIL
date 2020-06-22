 

시뮬레이터 설정….

 

 

공통

request

type : "sys"

cmd : "connect"

transactionId : [req의 transactionId]

result : OK/Fail

reason : Reason String

 

sys_connect_res

data

ip : Config에서 설정한 IP

amrCount : req의 amrCount

evsCount : req의 evsCount

filter

conditions[] : {...}

reason : "Condition Mismatch : {MIsmatched condition}"

 

sys_heartbeat_res

data

amrIdleCount : 사용 가능한 AMR Count

evsIdleCount : 사용 가능한 EVS Count

 

file_play_res

data

playTime : [Config값]

report

event : done/error/stopped

collectDigit : [Config값]

value : [Config값]

 

file_play_rpt

report

event : done/error/stopped

collectDigit : ???

value : 

 

dtmf_generate_rpt

report

event : done/error/stopped

value : 

 

dialog_stop_rpt

report

event :

value :

 

sys_stop_rpt

data

id : [config값]

udp

sent : 

received : 

class : [Config값]

state : oos

 

tool_status_rpt

report

event : done/timeout/error

value : [Config값]

data

active : [Config값]