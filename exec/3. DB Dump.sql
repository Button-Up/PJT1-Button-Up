 -- DROP DATABASE buttonup;
 CREATE DATABASE buttonup;
 USE buttonup;

-- 회원-------------------------------------------
DROP TABLE IF EXISTS parents;
CREATE TABLE `parents` (
	`parent_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`parent_name`	varchar(30)	NOT NULL,
	`parent_nickname`	varchar(30)	NOT NULL unique,
	`parent_password`	varchar(200)	NOT NULL,
	`parent_phone`	varchar(13) NOT NULL,
	`parent_image`	varchar(200),
	`parent_auth`              varchar(100),
	`parent_email`		varchar(50)	NOT NULL,
	`parent_tutorial_stage` int NOT NULL DEFAULT 1 
);
DROP TABLE IF EXISTS children;
CREATE TABLE `children` (
	`child_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`child_name`	varchar(30)	NOT NULL,
	`child_nickname`	varchar(30) NOT NULL unique,
	`child_password`	varchar(200)	NOT NULL,
	`child_phone`	varchar(13)	NOT NULL,
	`child_image`	varchar(200),
	`child_birth_date`	date,
	`child_auth`              varchar(100),
	`fk_parent_seq`	bigint
);

-- 구매 관리 / 요청-------------------------------------------
DROP TABLE IF EXISTS products;
CREATE TABLE `products` (
	`product_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`product_price`	int	NOT NULL,
	`product_name`	varchar(50),
	`product_description`	varchar(500),
	`product_type`	varchar(50) NOT NULL,
	`prodcut_purchase_link`	varchar(100),
	`product_image`	varchar(200),
	`fk_parent_seq`	bigint
);

DROP TABLE IF EXISTS request_histories;
CREATE TABLE `request_histories` (
	`request_history_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`request_history_type`	varchar(50)	NOT NULL,
	`request_history_date`	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`request_history_status` varchar(50) NOT NULL,
	`request_history_price`	int	NOT NULL,
	`fk_product_seq` bigint,
	`fk_child_seq`	bigint
);


-- 투자-------------------------------------------
DROP TABLE IF EXISTS investments;
CREATE TABLE `investments` (
	`investment_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`investment_target`	varchar(30),
	`investment_start_date`	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`fk_investment_preset_seq`	bigint,
	`fk_parent_seq`	bigint
);

DROP TABLE IF EXISTS investment_statuses;
CREATE TABLE `investment_statuses` (
	`investment_status_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`investment_status_count`	int	NOT NULL,
	`investment_status_average_price`	double	NOT NULL,
	`fk_investment_seq`	bigint,
	`fk_child_seq`	bigint
);


DROP TABLE IF EXISTS share_prices;
CREATE TABLE `share_prices` (
	`share_price_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`share_price_date`	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`share_price_price`	int	NOT NULL,
	`fk_investment_seq`	bigint
);

DROP TABLE IF EXISTS investment_presets;
CREATE TABLE `investment_presets` (
	`investment_preset_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`investment_preset_name`	varchar(30),
	`investment_preset_unit`	varchar(10)
);

DROP TABLE IF EXISTS news;
CREATE TABLE `news` (                                   
	`news_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`news_date`	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`news_content`	varchar(500)	NOT NULL,
	`fk_parent_seq`	bigint	
);


-- 적금 관리 / 단추-------------------------------------------

DROP TABLE IF EXISTS savings;
CREATE TABLE `savings` (
	`saving_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`saving_start_date`	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`saving_interest_rate`	double 	NOT NULL	DEFAULT 0.05,
	`saving_term`	int	DEFAULT 3,
	`saving_transfer_amount`	int,
	`saving_state_type_flag`	boolean	NULL,
	`fk_child_seq`	bigint
);

DROP TABLE IF EXISTS account_histories;
CREATE TABLE `account_histories` (
	`account_history_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`account_history_type`	varchar(10),
	`account_history_category`	varchar(50),
	`account_history_content`	varchar(30),
	`account_history_money`	int	NOT NULL	DEFAULT 0,
	`account_history_balance`	int	NULL,
	`account_history_date`	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`fk_child_seq`	bigint
);

DROP TABLE IF EXISTS saving_histories;
CREATE TABLE `saving_histories` (
	`saving_history_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`saving_history_money`	int	NULL,
	`saving_history_balance`	int	NULL,
	`saving_history_content`	varchar(30)	NULL,
	`saving_history_date`	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`fk_saving_seq`	bigint
);


-- 직업-------------------------------------------

DROP TABLE IF EXISTS jobs;
CREATE TABLE `jobs` (
	`job_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`job_pay_term`	varchar(20)	NOT NULL,
	`job_pay`	int	NOT NULL,
	`job_name`	varchar(30)	NOT NULL,
	`job_image_path`	varchar(225),
	`fk_parent_seq`	bigint
);
DROP TABLE IF EXISTS job_images;
CREATE TABLE `job_images` (
	`job_image_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`job_image`	varchar(200)	NOT NULL
);

DROP TABLE IF EXISTS job_histories;
CREATE TABLE `job_histories` (
	`job_history_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`job_history_start_date`	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`job_history_end_date`	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`fk_job_seq`	bigint,
	`fk_child_seq` bigint
);

DROP TABLE IF EXISTS todo_check_list;
CREATE TABLE `todo_check_list` (
	`todo_check_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`todo_check_flag`	boolean	NOT NULL DEFAULT false,
	`fk_todo_seq`	bigint,
	`fk_child_seq`	bigint
);


DROP TABLE IF EXISTS todo_list;
CREATE TABLE `todo_list` (
	`todo_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`todo_content`	varchar(500)	NOT NULL,
	`fk_job_seq`	bigint
);



-- 경제공부방-------------------------------------------
DROP TABLE IF EXISTS quizzes;
CREATE TABLE `quizzes` (
	`quiz_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`quiz_content`	varchar(200)	NOT NULL,
	`quiz_answer` varchar(100)	NOT NULL,
	`quiz_type`	varchar(50)	NOT NULL,
	`fk_economy_topic_seq`	bigint	
);

DROP TABLE IF EXISTS quiz_histories;
CREATE TABLE `quiz_histories` (
	`quiz_history_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`quiz_history_correct_number`	int	NOT NULL,
	`quiz_history_pass_flag`	boolean	NOT NULL,
	`fk_child_seq`	bigint,
	`fk_economy_topic_seq`	bigint
);


DROP TABLE IF EXISTS economy_posts;
CREATE TABLE `economy_posts` (
	`economy_post_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`economy_post_title`	varchar(100)	NOT NULL,
	`economy_post_image`	varchar(200),
	`economy_post_content`	varchar(500)	NOT NULL,
	`economy_post_order`	int	NOT NULL,
	`fk_economy_topic_seq`	bigint
);

DROP TABLE IF EXISTS economy_topics;
CREATE TABLE `economy_topics` (
	`economy_topic_seq`	bigint NOT NULL auto_increment PRIMARY KEY,
	`economy_topic_image`	varchar(200),
	`economy_topic_name`	varchar(100)	NOT NULL
);


-- 외래키 추가-------------------------------------------


ALTER TABLE children
ADD CONSTRAINT children_fk FOREIGN KEY (fk_parent_seq) REFERENCES parents(parent_seq) ON DELETE SET NULL;

ALTER TABLE products
ADD CONSTRAINT products_fk FOREIGN KEY (fk_parent_seq) REFERENCES parents(parent_seq) ON DELETE SET NULL;

ALTER TABLE request_histories
ADD CONSTRAINT request_histories_fk1 FOREIGN KEY (fk_product_seq) REFERENCES parents(parent_seq) ON DELETE SET NULL;

ALTER TABLE request_histories
ADD CONSTRAINT request_histories_fk2 FOREIGN KEY (fk_child_seq) REFERENCES children(child_seq) ON DELETE SET NULL;

ALTER TABLE investments
ADD CONSTRAINT investments_fk1 FOREIGN KEY (fk_investment_preset_seq) REFERENCES investment_presets(investment_preset_seq) ON DELETE SET NULL;

ALTER TABLE investments
ADD CONSTRAINT investments_fk2 FOREIGN KEY (fk_parent_seq) REFERENCES parents(parent_seq) ON DELETE SET NULL;

ALTER TABLE investment_statuses
ADD CONSTRAINT investment_statuses_fk1 FOREIGN KEY (fk_investment_seq) REFERENCES investments(investment_seq) ON DELETE SET NULL;

ALTER TABLE investment_statuses
ADD CONSTRAINT investment_statuses_fk2 FOREIGN KEY (fk_child_seq) REFERENCES children(child_seq) ON DELETE SET NULL;


ALTER TABLE share_prices
ADD CONSTRAINT share_prices_fk FOREIGN KEY (fk_investment_seq) REFERENCES investments(investment_seq) ON DELETE SET NULL;

ALTER TABLE news
ADD CONSTRAINT news_fk FOREIGN KEY (fk_parent_seq) REFERENCES parents(parent_seq) ON DELETE SET NULL;

ALTER TABLE savings
ADD CONSTRAINT savings_fk FOREIGN KEY (fk_child_seq) REFERENCES children(child_seq) ON DELETE SET NULL;

ALTER TABLE account_histories
ADD CONSTRAINT account_histories_fk FOREIGN KEY (fk_child_seq) REFERENCES children(child_seq) ON DELETE SET NULL;


ALTER TABLE saving_histories
ADD CONSTRAINT saving_histories_fk FOREIGN KEY (fk_saving_seq) REFERENCES savings(saving_seq) ON DELETE SET NULL;


ALTER TABLE jobs
ADD CONSTRAINT jobs_fk FOREIGN KEY (fk_parent_seq) REFERENCES parents(parent_seq) ON DELETE SET NULL;



ALTER TABLE job_histories
ADD CONSTRAINT job_histories_fk1 FOREIGN KEY (fk_job_seq) REFERENCES jobs(job_seq) ON DELETE SET NULL;

ALTER TABLE job_histories
ADD CONSTRAINT job_histories_fk2 FOREIGN KEY (fk_child_seq) REFERENCES children(child_seq) ON DELETE SET NULL;


ALTER TABLE todo_check_list
ADD CONSTRAINT todo_check_list_fk1 FOREIGN KEY (fk_todo_seq) REFERENCES todo_list(todo_seq) ON DELETE SET NULL;

ALTER TABLE todo_check_list
ADD CONSTRAINT todo_check_list_fk2 FOREIGN KEY (fk_child_seq) REFERENCES children(child_seq) ON DELETE SET NULL;


ALTER TABLE todo_list
ADD CONSTRAINT todo_list_fk FOREIGN KEY (fk_job_seq) REFERENCES jobs(job_seq) ON DELETE SET NULL;


ALTER TABLE quizzes
ADD CONSTRAINT quizzes_fk FOREIGN KEY (fk_economy_topic_seq) REFERENCES economy_topics(economy_topic_seq) ON DELETE SET NULL;

ALTER TABLE quiz_histories
ADD CONSTRAINT quiz_histories_fk1 FOREIGN KEY (fk_child_seq) REFERENCES children(child_seq) ON DELETE SET NULL;

ALTER TABLE quiz_histories
ADD CONSTRAINT quiz_histories_fk2 FOREIGN KEY (fk_economy_topic_seq) REFERENCES economy_topics(economy_topic_seq) ON DELETE SET NULL;

ALTER TABLE economy_posts
ADD CONSTRAINT economy_posts_fk FOREIGN KEY (fk_economy_topic_seq) REFERENCES economy_topics(economy_topic_seq) ON DELETE SET NULL;





insert into economy_topics values (1,'/512/3061/3061341','돈의 역사');

insert into economy_topics values (2,'/512/1138/1138038','은행에서 하는 일');

insert into economy_topics values (3,'/512/2474/2474455','은행이란?');

insert into economy_topics values (4,'/512/686/686379','저축이란?');

insert into economy_topics values (5,'/512/5501/5501360','투자란?');

insert into economy_topics values (6,'/512/639/639365','화폐란?');

insert into economy_posts values (1,'화폐는 왜 생겨났을까?',null,'<p>돈이 없던 아주 먼 옛날, 사람들은 필요한 물건을 어떻게 구했을까요? 옛날에는 자기가 가지고 있는 물건을 갖고 싶은 물건과 바꾸었어요. 이것을 물건과 물건의 교환, 즉 <strong>물물 교환</strong>이라고 한답니다.</p>
<p>그런데 물물 교환을 하다 보니 여러 가지 문제가 생겼어요. 좀 더 쉽게 물건을 교환하기 위해서 물품 <strong>화폐</strong>는 다시 <strong>금속 화폐</strong>와 <strong>지폐</strong>로 발전했고, 오늘날에는 신용을 기반으로 한 <strong>신용 화폐</strong>가 생겨났답니다.</p>',1,6);

insert into economy_posts values (2,'1. 화폐의 기능',null,'<p>화폐는 주로 물건을 사는 데 사용하지만 또 다른 기능도 있어요. 화폐의 세 가지 기능을 알아보아요.</p>
<ol>
<li><p>1. <strong>교환 매매의 기능</strong></p>
<p>화폐를 상품이나 서비스로 교환하는 것으로, 쌀이 필요하면 돈과 쌀을 교환하는 것을 말해요.</p>
</li>
<li><p>2. <strong>가치 척도의 기능</strong></p>
<p>상품의 가치를 화폐로 나타내는 것으로 물건에 매겨지는 가격이에요. 우리는 가격을 보고 상품의 가치를 금방 알 수 있지요. 흔하고 어디서나 쉽게 구할 수 있으면 가격이 싸고, 귀한 것일수록 가격이 비싸니까요.</p>
</li>
<li><p>3. <strong>가치 저장의 기능</strong></p>
<p>돈을 가지고 있다는 자체가 재산이며, 언제든지 물건을 살 수 있다는 구매력을 뜻해요. 돈을 저금하는 것은 이러한 가치를 저장하는 것과 같답니다.</p>
</li>

</ol>',2,6);

insert into economy_posts values (3,'2.화폐의 변천 - 금의 등장','https://cdn-icons-png.flaticon.com/512/646/646634.png','<p>화폐는 우선 가지고 다니기 편해야 하고 나눠 가지기 쉬워야 하고 누구나 가지고 싶어 하며 가치가 변하지 않는 것이어야 했어요. 이런 조건에 맞는 물품이 조개 껍데기, 돌로 만든 도구, 볍씨, 토기, 청동 방울, 청동검, 철로 만든 농기구, 옷감, 가죽, 쌀, 베, 소금 등이었답니다.</p>
<p>조개 껍데기와 볍씨 같은 물품 화폐는 오랜 시간이 지나면 깨지고 썩어서 못 쓰게 되어 불편했어요. 그래서 새로운 것을 찾기 시작했는데, 오랫동안 변하지 않고 누구나 갖고 싶어하는 것이 바로 <strong>금</strong>이었답니다.</p>',3,6);

insert into economy_posts values (4,'2. 화폐의 변천 - 금의 문제점',null,'그런데 금으로 화폐를 만들자 문제가 생겼어요. 어떤 문제일까요? 맞아요. <strong>금은 도난의 위험이 크고, 여행을 다닐 때는 무거워서 가지고 다니기가 힘들었어요.</strong> 그래서 지금과 같은 편리한 지폐와 동전이 나오게 된 것이랍니다.',4,6);


insert into economy_posts values (5,'1. 돈의 역사',null,'<p>아주 오랜 옛날에는 필요한 물건을 직접 만들어 쓰거나 다른 사람의 물건과 바꾸어 썼어요. 하지만 생활하는 데 불편함이 많았지요.</p>
<p>그래서 좀 더 편리하게 물건을 바꿀 방법이 없을까 생각해 낸 것이 바로 <strong>&#39;돈&#39;</strong>이랍니다.</p>
<p>오늘날의 돈이 만들어지기까지의 과정을 살펴보아요.</p>',1,1);

insert into economy_posts values (6,'2. 물물 교환','https://cdn-icons-png.flaticon.com/512/4496/4496184.png','<p>돈이 없었던 아주 오랜 옛날에는 각자 자기가 필요한 물건들을 직접 만들어 썼어요. 농사도 짓고, 가축도 기르며, 옷도 직접 지어 입었지요. 그러다가 자기가 사용하고 남은 물건과 다른 사람이 사용하고 남은 물건을 바꾸어 쓰기 시작했어요. <strong>물물 교환</strong>을 하기 시작한 것이지요. 하지만 원하는 물건을 가진 사람을 찾는 일은 쉽지 않았어요.</p>',2,1);

insert into economy_posts values (7,'3. 물품 화폐','https://cdn-icons-png.flaticon.com/512/4792/4792308.png','<p>물물 교환의 불편함을 느낀 사람들은 공통으로 사용하는 기준 물품이 있으면 좋겠다고 생각했어요. 그래서 <strong>조개껍질, 깃털, 소금, 곡식, 옷감, 농기구</strong> 같은 물건을 화폐처럼 사용하기 시작했는데, 이런 것들을 <strong>&#39;물품 화폐&#39;</strong>라고 불러요.</p>
<p>처음에는 곡식이나 가죽처럼 생활 필수품을 물품 화폐로 사용했어요. 그러나 이런 물품은 생산량에 따라 가치가 바뀌기 때문에 나중에는 <strong>비교적 가치가 일정하고 보관과 운반이 쉬운</strong> 조개 껍질, 옷감, 농기구, 장신구 등을 사용했어요.</p>',3,1);

insert into economy_posts values (8,'4. 금속 화폐','https://cdn-icons-png.flaticon.com/512/646/646634.png','<p>물물 교환 대신 물품 화폐를 사용했지만, 그것 또한 불편한 점이 있었어요. 깃털은 가벼워서 쉽게 날아가 버리고, 소금은 비가 오면 녹아 버리기 일쑤였지요. 그래서 사람들은 다시 작고 다루기 쉬운 교환 수단을 생각해 냈어요. 그것이 바로 <strong>금, 은, 청동, 철과 같은 금속들</strong>이었지요. 하지만 금속 화폐는 일정한 모양이 없어서 관리하기가 어려웠어요.</p>',4,1);

insert into economy_posts values (9,'5. 주조 화폐','https://cdn-icons-png.flaticon.com/512/1468/1468491.png','<p>금속 화폐는 쓸 때마다 저울로 무게를 달아야 해서 불편했어요. 그래서 금속을 녹여 일정한 무게와 모양으로 화폐를 만들었지요. 이것을 <strong>&#39;주조 화폐&#39;</strong>라고 해요. 기원전 700년쯤 소아시아에서 그림을 새겨 넣은 납작한 금속을 사용했다는 기록이 있는데, 이것이 <strong>세계 최초 동전</strong>으로 여겨지고 있어요.</p>',5,1);

insert into economy_posts values (10,'6. 지폐','https://cdn-icons.flaticon.com/png/128/625/premium…=1645081114~hmac=c959429e316dac3bf8926766a30a3489','<p>동전은 무거워서 많은 양을 운반하기가 어려웠어요. 그래서 가볍고 사용하기 <strong>편리한 종이로 지폐</strong>를 만들었지요. 우리나라 최초의 지폐는 저나무 껍질로 만든 저화인데, 오늘날에는 전하지 않아요. 지금 우리가 쓰는 지폐는 <strong>한국 조폐 공사</strong>에서 만들어요. 2009년 6월에는 오만원권이 새로 나왔어요.</p>',6,1);

insert into economy_posts values (11,'7. 전자화폐','https://cdn-icons-png.flaticon.com/512/1087/1087080.png','<p>오늘날 물건의 대가를 지급하는 방법으로 <strong>현금</strong> 말고도 <strong>수표, 직불 카드, 신용 카드</strong> 등이 있어요. 승차권이나 상품권 등도 대가를 지급하는 방법으로 사용되기도 하지요. 또 요즘에는 지폐나 동전 대신 <strong>전자 화폐</strong>를 사용하기도 해요.</p>
<p>지금 쓰이는 전자 화폐는 플라스틱 카드에 전자 칩을 넣어서 사용하는 IC 형과 인터넷으로 돈을 거래하는 네트워크 형이 있어요.</p>',7,1);

insert into economy_posts values (12,'1. 예금',null,'<p>개인이나 기업으로부터 <strong>예금</strong>을 받아요. 개인과 기업이 <strong>예금</strong>하면 이것을 필요한 개인과 기업에 대출해 주어서 각종 수수료와 이자를 받아요. 은행은 대출해 준 개인과 기업에게서 받은 이자의 일부를 <strong>예금</strong>한 사람에게 이자로 주어요.</p>',1,2);

insert into economy_posts values (13,'2. 공과금 수납',null,'<p>세금이나 공과금, 등록금 등을 받아요. 은행에 각종 세금을 내러 굳이 국세청이나 세무서로 가지 않아도 돼요. 아파트 관리비도 마찬가지지요. 이처럼 각종 <strong>공과금</strong>을 은행에서 대신 받아주어요.</p>',2,2);

insert into economy_posts values (14,'3. 외환 업무',null,'<p>외국의 돈을 우리나라의 돈으로 바꿔주어요. 무역을 하는 사업가와 해외관광객은 여행과 무역결재에 필요한 다른 나라의 돈을 은행에서 편리하게 바꿀 수 있어요.이 외에 은행은 개인의 귀금속, 보석, 유가 증권 등을 보관해 주고, 거래 기업의 경영 상태에 대해 상담도 해 주어요. 또한 신용 카드를 발급하고 결재하는 일도 해요.</p>',3,2);

insert into economy_posts values (15,'4. 대출',null,'<p>은행은 돈이 필요한 사람에게 이자를 받고 돈을 빌려주는데 그걸 <strong>대출</strong>이라고 해요. 담보 대출과 신용 대출이 있어요. 담보 대출은 지금 현금은 없지만 마음만 먹으면 팔아서 갚을 수 있는 재산이나 능력을 맡기고 빌리는 거에요. 집이나 땅, 연금이나 퇴직금, 사업실적 등을 담보로 할 수 있지요. 신용대출은 신용회사나 다른 능력이 있는 사람이 ‘이 사람은 돈을 갚을 능력이 됩니다’라는 신용보증을 해주면 받을 수 있어요.</p>',4,2);

insert into economy_posts values (16,'1. 은행이란?','https://cdn-icons-png.flaticon.com/512/1029/1029879.png','<p>은행<strong>은 금융 기관이에요. </strong>은행**은 우리가 저축한 돈을 모아 필요한 회사와 사람들에게 돈을 빌려 주어요. 우리가 저축한 돈은 회사에 필요한 자금이 되어 일자리를 늘려 주고 경제가 활발하게 하는 데 도움을 주지요.</p>
<p>은행은 우리가 저축한 돈을 투자하여 돈을 불린 다음 우리에게 이자를 주어요. <strong>은행</strong>은 크게 중앙 은행과 일반 은행, 그리고 특수 은행으로 나눌 수 있어요.</p>',1,3);

insert into economy_posts values (17,'2-1. 은행의 종류 - 중앙 은행','https://cdn-icons-png.flaticon.com/512/3635/3635995.png','<p><strong>중앙 은행</strong>은 각 나라마다 하나씩 있는데, 우리나라 중앙 은행은 바로 <strong>한국 은행</strong>이에요. <strong>한국 은행</strong>은 화폐를 발행할 뿐 아니라, 통화량 조절을 하기도 해요. 통화량 조절이란 돈이 너무 많이 시중에 풀리면 돈을 거둬들이고, 부족하면 돈을 내보내는 등 나라에서 쓰이는 돈의 양을 조절하는 것을 말해요.일반 은행도 개인이나 기업처럼 한국 은행에 돈을 맡기거나 대출을 받기도 해요. 그래서 <strong>한국 은행</strong>을 &#39;은행의 은행&#39;이라고도 하지요. 또 한국 은행은 정부에서 필요한 돈을 빌려 주기도 하고, 나라를 대표해서 외국과의 거래에 필요한 외화를 관리해 주기도 하기 때문에 <strong>&#39;정부의 은행&#39;</strong>이라고도 한답니다.</p>',2,3);

insert into economy_posts values (18,'2-2. 은행의 종류 - 일반 은행','https://cdn-icons-png.flaticon.com/512/2398/2398987.png','<p>예금을 하거나 돈을 찾고 빌릴 때, 또는 세금을 낼 때 우리가 쉽게 이용하는 은행이 대부분 <strong>일반 은행</strong>이에요. <strong>일반 은행</strong>은 사람이나 회사를 대신해서 돈을 보내고, 받고, 내주는 일을 한답니다.</p>',3,3);

insert into economy_posts values (19,'2-3. 은행의 종류 - 특수 은행','https://cdn-icons-png.flaticon.com/512/1283/1283187.png','<p><strong>특수 은행</strong>은 특수한 목적을 위해 만들어진 은행이에요. 중소 기업의 지원을 위한 중소 기업 은행, 농민과 어민을 지원하는 농업 협동 조합과 수산업 협동 조합, 우리나라의 주요 산업에 돈을 대 주는 한국 산업 은행 등이 있답니다.</p>',4,3);

insert into economy_posts values (20,'1. 저축이란?',null,'<p>아껴 쓰고 모으는 것<strong>을 </strong>저축이라고 해요. 여러분의 집에서도 대부분 저축을 하고 있을 거예요. 은행이나 우체국 등 금융 기관에 돈을 맡기기도 하고, 주식에 투자하거나 보험에 가입하기도 하지요. 이렇게 가정마다 저축을 하는 이유는 다양하답니다. 그런데 저축은 왜 하는 걸까요?저축은 생각지도 못한 재난에 대비하기 위해서 한답니다. 예고 없이 찾아오는 사고나 질병, 홍수나 화재 등에 대비하기 위해서 저축을 하는 것이지요. 그리고 미래에 대비하기 위한 목적으로도 저축을 해요.</p>
<p>예를 들면 지금 살고 있는 집에서 좀 더 넓은 집으로 옮기기 위해서, 혹은 자동차를 새로 바꾸기 위해서 저축을 하기도 하지요. 그리고 안정된 노후 생활을 위해서도 저축은 필요하답니다.</p>',1,4);

insert into economy_posts values (21,'2. 저축과 이자','https://cdn-icons-png.flaticon.com/512/1606/1606585.png','<p>은행에 돈을 예금하면 이자가 붙어요. 반대로 은행에서 대출을 하면 이자를 내야 돼요. 돼지 저금통에 10만 원을 넣고 1년 동안 있어도 10만 원은 변함이 없잖아요.</p>
<p>그런데 은행에 돈을 맡기면 이자가 붙어 소득이 늘어날 수 있고, 내가 저축한 돈이 경제 발전에 도움이 돼서 좋고, 절약하는 습관을 길러서 좋으니 일석 삼조가 아니겠어요? 자, 우리 모두 저축하러 은행으로 출발!</p>',2,4);

insert into economy_posts values (22,'3. 저축할 돈을 마련할 수 있는 방법',null,'<p>우리들이 저축을 할 수 있는 방법에는 무엇이 있을까요? 우선 군것질을 줄여서 용돈을 아껴 쓸 수 있어요. 명절 때 받은 돈을 모으거나 학용품을 아껴 쓰고 잃어버리지 않는 것도 좋은 방법이에요. 벼룩 시장에 안 쓰는 물건을 내다 파는 등 아르바이트를 해서 돈을 버는 것도 좋지만, <strong>무엇보다 쓸데없는 지출을 줄이는 것이 저축의 지름길이겠죠?</strong> 아, <strong>첫 단추의 투자</strong>를 활용하는 것도 하나의 방법이 될 수 있겠어요!</p>',3,4);

insert into economy_posts values (23,'4. 저축이 필요한 이유','https://cdn-icons-png.flaticon.com/512/3144/3144888.png','<ol>
<li><strong>예측할 수 없는 사고에 대비하기 위해서</strong></li>
<li><strong>미래에 대비하기 위해서</strong></li>
<li><strong>자신이 좀 더 편안한 생활을 하기 위해서</strong></li>
<li><strong>안정된 노후 생활을 위해서</strong></li>

</ol>',4,4);

insert into economy_posts values (24,'1. 투자란?','https://cdn-icons-png.flaticon.com/512/6868/6868508.png','<p><strong>이익을 얻기 위해서 어떤 일이나 사업에 필요한 돈을 대거나 시간과 정성을 쏟는 것</strong>이 <strong>투자</strong>라고 해요. 투자는 꼭 돈으로만 하는 건 아니에요. 우리가 공부나 운동을 열심히 하고 특별한 재능을 키우는 데 노력하는 것도 미래를 위해 투자하는 것이랍니다.</p>',1,5);

insert into economy_posts values (25,'2. 투자하는 방법',null,'<p><strong>투자는 이익을 얻기 위해서 어떤 일이나 사업에 필요한 돈을 대거나 시간과 정성을 쏟는 거예요.</strong> 투자를 하면 <strong>은행에 저축하는 것보다 짧은 시간에 더 많은 이익</strong>을 얻을 수 있어요. 하지만 그만큼 <strong>손해를 볼 위험도 크지요</strong>. 따라서 투자를 할 때에는 <strong>곰곰이 생각해 보고 결정</strong>해야 해요.</p>
<p>투자는 돈으로만 할 수 있는 게 아니에요. <strong>사람의 능력이나 기술, 지식, 건강</strong> 같은 데에도 투자할 수 있어요. 여러분이 공부나 운동을 열심히 하고 특별한 재능을 키우는 것 모두가 미래를 위해 투자하는 것이랍니다. 이러한 투자를 통해서 우리나라의 경제가 더욱 발전할 수 있지요.</p>
',2,5);



insert into economy_posts values (26,'3. 주식이란 무엇인가',null,'
<p><strong>주식회사</strong>는 사람들에게 <strong>주식을 팔아 그 돈으로 회사를 세우고 경영</strong>하는 회사예요. <strong>주식</strong>은 <strong>주식회사에서 만들어 파는 증서</strong>이지요. <strong>주주</strong>는 주식을 사서 가지고 있는 사람은 주식의 주인이에요.자신이 가지고 있는 주식만큼 그 회사의 주인이 되는 거랍니다.</p>
<p>주식을 발행해서 사고파는 것은 어느 회사나 할 수 있는 게 아니에요. 회사가 그만한 자격을 갖추고 있어야 해요. 주식회사가 회사를 잘 경영해서 이익이 생기면 주식 값도 올라가요. 그러면 그 회사 주식을 산 사람들은 주식 값이 오른 만큼 돈을 벌게 되지요. 반대로 주식 값이 떨어지면 손해를 보게 되고요. 그래서 주식을 살 때에는 어떤 회사의 주식을 살지 신중하게 생각해야 한답니다.</p>',3,5);

insert into economy_posts values (27,'4. 주식을 사고파는 증권 회사',null,'<p>증권 회사<strong>는 </strong>주식을 사고파는 일을 대신해 주는 회사예요.</p>
<p>어느 회사의 주식을 살지 정했으면 증권 회사에 가요. 증권 회사에 처음 가면 먼저 거래 계좌를 만들어야 해요. 은행 거래를 하기 위해서 통장 계좌를 만드는 것과 비슷해요. 증권 회사의 계좌를 통해서 주식을 사고팔 수 있어요.</p>
<p>주식은 주주 마음대로 사고 싶을 때 사고, 팔고 싶을 때 팔 수 있어요. 하지만 주식의 값이 올랐다 내렸다 하기 때문에 잘 알아보고 주식을 사고파는 시기를 결정해야 하지요.</p>
<p><strong>주식을 사고파는 주식 시장</strong> 우리 주변에는 여러 종류의 시장이 있어요. 그중에서 주식을 사고파는 시장은 주식 시장이라고 해요. 직접 주식을 사고파는 증권거래소나 컴퓨터와 통신망을 이용하여 주식을 사고파는 코스닥 등이 주식 시장이랍니다.</p>',4,5);

insert into economy_posts values (28,'5. 내용 요약',null,'<p>주식회사는 주식을 발행하고 팔아서 그 돈으로 세워진 회사예요.</p>
<p><strong>증권 회사</strong>는 개인을 대신하여 주식을 사거나 팔아 줘요.</p>
<p>주식회사에 이익이 생기면 주식 값이 올라가고 <strong>주주들은 오른 만큼 돈을 벌어요</strong>. <strong>주식을 살 때에는 어떤 회사의 주식을 살지 신중하게 선택해야 해요.</strong></p>',5,5);

insert into investment_presets values (1,'몸무게','kg');

insert into investment_presets values (2,'퇴근시간','시');

insert into parents values(1,'신형만','shin04','1234','010-2555-2222',null,'user','shin@naver.om',0);

insert into children values(1,'신짱구','jjangu99','1234','010-2131-5465',null,null,'user',1);

insert into children VALUES(2,'신짱아','jjanga99','1234','010-2131-5464',null,null,'user',1);

insert into jobs values(1,'주급',5000,'펫시터',null,1);

insert into jobs values(2,'주급',10000,'환경미화원',null,1);

insert into todo_list values(1,'산책시키기',1);

insert into todo_list values(2,'강아지 화장실 치우기',1);

insert into todo_list values(3,'물 갈아주기',1);

insert into todo_list values(4,'책상 닦기',2);

insert into todo_list values(5,'휴지통 비우기',2);

insert into todo_list VALUES(6,'음식물 쓰레기 버리기',2);

insert into job_histories values(1,current_timestamp(),null,1,1);

insert into job_histories values(2,current_timestamp(),null,2,2);

insert into todo_check_list values(1,0,1,1);

insert into todo_check_list values(2,0,2,1);

insert into todo_check_list values(3,0,3,1);

insert into todo_check_list values(4,0,4,2);

insert into todo_check_list values(5,0,5,2);

insert into todo_check_list values(6,0,6,2);

insert into savings values(1,current_timestamp(),0.05,100,0,1,1);


