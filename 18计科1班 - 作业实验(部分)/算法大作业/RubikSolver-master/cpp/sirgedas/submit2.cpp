#include <string>
#include <iostream>
using namespace std;
#define SH <<
#define PP = -1; ++
#define _F ;)
#define FOR_i for (int i PP i < __LINE__%21 _F
#define FOR_k for (int k PP k <
#define _E );
#define _G ;}
#define _I [i
#define _Q ){
#define FOR_cur_phase ; for (cur_phase PP cur_phase < 4 _F
#define _48_ [48]
#define Ci12 cubelet _I +12].
#define Ci cubelet _I ].
#define FOR_RET2 ret += ret +
#define _IF if (
string data = "2#6'&78)5+1/AT[NJ_PERLQO@IAHPNSMBJCKLRMSDHEJNPOQFKGIQLSNF@DBROPMAGCEMPOACSRQDF";
char inva _48_, b _48_, cur_phase, search_mode, history_idx, history_mov _48_, history_rpt _48_, depth_to_go[5 SH __LINE__], hash_table _48_[6912];
#define PA cubelet[64^data[20+cur_phase*8+i
struct Cubelet { char pos, twi _G cubelet _48_;
#define PB ]]
void rot(char cur_phase _Q
	_IF cur_phase < 4) FOR_i PA PB.twi = (PA PB.twi + 2 - i%2) % 3, PA +4 PB.twi ^= cur_phase < 2;
	
	
	FOR_i swap(PA +(i!=3) PB, PA PB _E
_G
int hashf( _Q	int ret = 0;	switch(cur_phase _Q
	case 0:
		FOR_i FOR_RET2 Ci twi;	
		return ret;

	case 1:





#define RRR ; FOR_i FOR_RET2 (Ci pos >







		FOR_i ret = ret*3 + Ci12 twi		



		RRR 7 _E		
		return ret-7;


#define INVABA (inva[b[0 PB ^inva[b[
	case 2:
		










		
		FOR_i  
			_IF Ci12 pos<16) inva[Ci12 pos&3] = ret++; else b _I -ret] = Ci12 pos&3	


















		RRR 3 _E				FOR_i FOR_RET2 (Ci12 pos > 15 _E		
		return ret*54 + INVABA 1 PB )*2 + (INVABA 2 PB ) > INVABA 3 PB )) - 3587708
	_G
	















	FOR_i {
		ret *= 24;		int cur_phase
		FOR_cur_phase			FOR_k cur_phase _F
				_IF cubelet _I *4+cur_phase].pos < cubelet _I *4+k].pos) ret += cur_phase SH cur_phase/3
	_G
	return ret/2
_G
#define H0 hash_table[cur_phase  ][h%q]
#define H1 hash_table[cur_phase+4][h/q]
int do_search(int dpt _Q
	int h = hashf(), q = cur_phase/2*19+8 SH 7;
	_IF (dpt < H0 | dpt < H1) ^ search_mode _Q
		_IF search_mode) 
			_IF dpt <= depth_to_go[h]) return !h;	
			else depth_to_go[h] = dpt;
		
		H0 <?= dpt;
		H1 <?= dpt;
		
		FOR_k 6 _F 
			FOR_i {
				rot(k _E
				_IF k < cur_phase*2 & i != 1 || i > 2) continue;	
				history_mov[history_idx] = k;		
				history_rpt[history_idx++] = i;
				_IF do_search(dpt-search_mode*2+1)) return 1;
				history_idx--
		_G
	}
	return 0
_G



int main(int, char** arg _Q
	memset(hash_table, 6, sizeof(hash_table) _E
	FOR_i Ci pos = i		FOR_cur_phase 		do_search(0 _E	FOR_i {
		string s = arg _I +1] + string("!" _E
		Ci pos = data.find(s[0] ^ s[1] ^ s[2] _E
		int x = min(s.find(85), s.find(68) _E
		Ci twi = ~x ? x : s[0]>70
	_G
	FOR_i swap(PA +16 PB, PA +21 PB _E	

	search_mode = 1











	FOR_cur_phase 
		FOR_i _IF do_search(i)) break;

	FOR_k history_idx _F
		cout  SH  "FBRLUD"[history_mov[k PB  SH  history_rpt[k]+1  SH  " ";

	return 0
_G
