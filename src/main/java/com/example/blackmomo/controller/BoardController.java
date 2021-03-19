package com.example.blackmomo.controller;

import com.example.blackmomo.domain.*;
import com.example.blackmomo.service.BoardService;
import com.example.blackmomo.service.BoardServiceImpl;

import com.example.blackmomo.service.FileService;
import com.example.blackmomo.util.MD5Generator;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    /*BoardServiceImpl boardServiceImpl;*/
    BoardService boardService;

    @Autowired
    FileService fileService;

    /**
     * <p>게시판 목록</p>
     * @param vo 페이징 정보
     * @param model
     * @param search 검색
     * @param orderBy 정렬종류
     * @param orderType 정렬타입
     * @param searchType 검색타입
     * @param keyword 검색키워드
     * @param nowPage 현재페이지 번호
     * @param cntPerPage 노출목록수
     * @return 목록으로 이동
     * @throws Exception
     */
    @GetMapping("/list")
    public String list(Paging vo, Model model, Search search,
                       @RequestParam(value = "orderBy", required = false) String orderBy,
                       @RequestParam(value = "orderType", required = false) String orderType,
                       @RequestParam(value = "searchType", required = false) String searchType,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "nowPage", required = false) String nowPage,
                       @RequestParam(value = "cntPerPage", required = false) String cntPerPage) throws Exception {

        logger.info("read");

        // 게시판 목록의 정렬순
        if (orderBy == null ){
            search.setOrderBy("id");
        }
        if (orderType == null){
            search.setOrderType("DESC");
        }
        if (searchType == null){
            search.setSearchType("all");
        }else if (searchType != null){
            search.setSearchType(searchType);
        }
        if (keyword == null){
            search.setKeyword("");
        }else if (keyword != null){
            search.setKeyword(keyword);
        }
       /* search.setKeyword(keyword);*/

        // 오름차순 내림차순은 추후 진행
        /*if (orderType == "DESC"){

        }*/

        /*System.out.println("요청값 확인 getOrderBy :::" +  search.getOrderBy());
        System.out.println("요청값 확인 getOrderType :::" +  search.getOrderType());
        System.out.println("요청값 확인 searchType :::" +  search.getSearchType());
        System.out.println("요청값 확인 keyword :::" +  search.getKeyword());*/

        // 초기 페이징값 설정
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "5";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "5";
        }
        int total = 0;

        vo = new Paging(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

        // 게시글수 조회
        total = boardService.countBoard(vo, search);

        vo = new Paging(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

        List<Board> boardList = boardService.findList(vo, search);

        model.addAttribute("paging", vo);
        model.addAttribute("search", search);
        model.addAttribute("total", total);
        model.addAttribute("postList", boardList);

        return "/board/list.html";
    }

    /**
     * <p>등록화면</p>
     * @param model
     * @return
     */
    @GetMapping("/post")
    public String post(Model model) {

        return "/board/form.html";
    }

    /**
     * <p>등록처리</p>
     * @param files
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/post")
    public String write(@RequestParam("file") MultipartFile files, Board board) throws Exception {


        board.setModifier(board.getRegisterer());

        try {
            if (!"".equals(files.getOriginalFilename())){
                String origFilename = files.getOriginalFilename();

                String filename = new MD5Generator(origFilename).toString();
                String savePath = System.getProperty("user.dir") + "\\files";
                /*String savePath = "C:\\workspace_ij\\demo\\files";*/

                File uploadDir = new File(savePath);

                // 실행되는 위치 'files' 폴더에 파일이 저장됨

                if (!uploadDir.exists()) {

                    // 파일 폴더가 없을 경우 파일 폴더 생성
                    try{
                        //.mkdir() / .mkdirs()
                        uploadDir.mkdir();

                    }
                    catch(Exception e){
                        e.getStackTrace();
                    }
                }

                // System.currentTimeMillis() = 현재  시간을 구하는 함수
                filename = System.currentTimeMillis() + "_" + filename + ".png";

                String filePath = savePath + "\\" + filename;



                File fileDir = new File(filePath);

                files.transferTo(fileDir);

                // 실행되는 위치 'files' 폴더에 파일이 저장됨
                FileDto fileDto = new FileDto();
                fileDto.setOrigFilename(origFilename);
                fileDto.setFilename(filename);
                fileDto.setFilePath(filePath);

                int fileId = fileService.saveFile(fileDto);
                board.setFileId(fileId);
            }
            else if ("".equals(files.getOriginalFilename())){
                board.setFileId(0);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        boardService.findSave(board);

        return "redirect:/board/list";
    }

    /**
     * <p>상세화면</p>
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") int id, Model model) throws Exception{

        // 조회수 갱신
        boardService.boardCount(id);

        Board boardDate = boardService.findView(id);

        FileDto file = null;

        if(boardDate.getFileId() > 0 ) {
            file = fileService.selectFile(boardDate.getFileId());
        }

        // 이전글 정보
        Board boardPrev = boardService.prevSelect(id);
        // 다음글 정보
        Board boardNext = boardService.nextSelect(id);

        /*System.out.println("이전글 : " + boardPrev.getId());*/
        /*System.out.println("다음글 : " + boardNext.getId());*/

        model.addAttribute("view", boardDate);
        model.addAttribute("file", file);
        model.addAttribute("boardPrev", boardPrev);
        model.addAttribute("boardNext", boardNext);
        return "board/view.html";
    }

    /**
     * <p>수정화면으로 이동</p>
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String boardEdit(@PathVariable("id") int id, Model model, RedirectAttributes rttr) {

        Board board = boardService.findSelectEdit(id);
        FileDto fileDto = fileService.findSelectEdit(board.getFileId());
        model.addAttribute("edit", board);
        model.addAttribute("file", fileDto);

/*        rttr.addFlashAttribute("edit", board);
        rttr.addFlashAttribute("file", fileDto);*/

        return "board/edit.html";
    }

    /**
     * <p>수정처리</p>
     * @param files
     * @param id
     * @param board
     * @param model
     * @return
     */
    @PostMapping("/edit/{id}")
    public String Edit(@RequestParam("file") MultipartFile files, @PathVariable("id") int id,Board board, Model model) {

        System.out.println("수정 정보확인 files ::::" + files.getOriginalFilename());
        System.out.println("수정 정보확인 id ::::" + id);
        System.out.println("수정 정보확인 board ::::" + board.getFileId());

        try{
            FileDto file= fileService.fileName(board.getFileId());
            // 파일 등록
            if (board.getFileId() == 0){
                if(!"".equals(files.getOriginalFilename())){
                    System.out.println("등록 진행");
                    board.setFileId(fileSave(files, board));
                }
            }
            
            // 파일을 삭제 및 수정
            else if(board.getFileId() > 0) {
                // 파일 삭제
                if ("".equals(files.getOriginalFilename())) {
                    System.out.println("삭제 진행");
                    // 기존파일 삭제
                    File deleteFolder = new File(file.getFilePath());
                    deleteFolder.delete();
                    fileService.fileDelete(board.getFileId());
                    board.setFileId(0);
                }
                // 수정
                else if(!"".equals(files.getOriginalFilename())){
                    // 파일명이 등록된 파일명과 다를 경우 기존파일 삭제 후, 새로이 등록된 파일로 등록

                    System.out.println("등록 파일명 확인 :::" + files.getOriginalFilename());
                    System.out.println("db 파일명 확인 :::" + file.getOrigFilename());
                    if (!"".equals(file.getOrigFilename())) {
                        if (file.getOrigFilename() != files.getOriginalFilename()) {
                            // 기존파일 삭제
                            File deleteFolder = new File(file.getFilePath());
                            deleteFolder.delete();
                            // 새로이 등록된 파일로 등록
                            board.setFileId(fileSave(files, board));
                        }
                    }
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        boardService.updateAll(board);

        return "redirect:/board/list";
    }

    private int fileSave(MultipartFile files, Board board) throws Exception {
        // 새로이 등록된 파일로 등록
        String origFilename = files.getOriginalFilename();
        String filename = new MD5Generator(origFilename).toString();
        String savePath = System.getProperty("user.dir") + "\\files";

        File uploadDir = new File(savePath);

        if(!uploadDir.exists()){
            try{
                //.mkdir() / .mkdirs()
                uploadDir.mkdir();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        filename = System.currentTimeMillis() + "_" + filename + ".png";
        String filePath = savePath + "\\" + filename;

        // 실행되는 위치 'files' 폴더에 파일이 저장
        File fileDir = new File(filePath);
        files.transferTo(fileDir);

        // db에 파일 정보 등록
        FileDto fileDto = new FileDto();

        if (board.getFileId() == 0){
            fileDto.setOrigFilename(origFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);
        }
        else if (board.getFileId() > 0){
            fileDto.setId(board.getFileId());
            fileDto.setOrigFilename(origFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);
        }
        return fileService.saveFile(fileDto);
    }

    @GetMapping(value = "delBoard/{id}")
    public String delBoard(@PathVariable("id") int id)  {

        if (id <= 0 ) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/board/list";
        }
        try{
            boolean isDeleted = boardService.findDel(id);
            if (isDeleted == false) {
                // TODO => 게시글 삭제에 실패하였다는 메시지를 전달
                System.out.println("게시글 삭제에 실패하였습니다. 재확인하세요");
            }
        } catch (DataAccessException e) {
            // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
            System.out.println("오류가 발생했습니다 : "+e.getMessage());

        } catch (Exception e) {
            // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
            System.out.println("오류가 발생했습니다 : "+e.getMessage());
        }
        return "redirect:/board/list";
    }


    @RequestMapping(value = "/download/{fileId}")
    public ResponseEntity<InputStreamResource> fileDownload(@PathVariable("fileId")int fileId) throws IOException {
        System.out.println("값 확인" + fileId);

        FileDto fileDto = fileService.getFile(fileId);

        Path path = Paths.get(fileDto.getFilePath());

        System.out.println("path 정보 ::::::: " + path );
        InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));

        System.out.println("path 데이터 ::::::: " + path);
        System.out.println("확인 :::::::: " + (ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource)));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/json; charset=utf8"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);

        /*Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);*/
    }

    /*public String makeSearch(int page)
    {

        UriComponents uriComponents =
                UriComponentsBuilder.newInstance()
                        .queryParam("page", page)
                        .queryParam("perPageNum", cri.getPerPageNum())
                        .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
                        .queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
                        .build();
        return uriComponents.toUriString();
    }

    private String encoding(String keyword) {
        if(keyword == null || keyword.trim().length() == 0) {
            return "";
        }

        try {
            return URLEncoder.encode(keyword, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            return "";
        }
    }*/

    /* 댓글목록 */
    @RequestMapping("/reply/list")
    public ModelAndView replyList(int bno, ModelAndView mav) throws Exception {

        Board boardDate = boardService.findView(bno);
        FileDto file = null;

        if(boardDate.getFileId() > 0 ) {
            file = fileService.selectFile(boardDate.getFileId());
        }

        // 댓글내용, 작성자번호, 작성자명, 작성일시, 수정일시
        int replyTotal = 0; // 댓글 수
        replyTotal = boardService.count(bno);

        List<Reply> replyList = boardService.list(bno); //댓글 목록
        mav.setViewName("/board/reply"); //뷰의 이름
        mav.addObject("replyList", replyList); //뷰에 전달할 데이터 저장
        mav.addObject("replyTotal", replyTotal); //뷰에 전달할 댓글수
        mav.addObject("file", file);
        mav.addObject("view", boardDate);
        return mav; //뷰로 이동
    }

    //댓글 목록을 ArrayList로 리턴
    /*@RequestMapping("/list_json")
    public List<Reply> list_json(int bno){ return boardServiceImpl.list(bno); }*/

    /**
     * <p>댓글등록</p>
     * @param dto
     * @param session
     */
    @RequestMapping("/reply/insert") //세부적인 url pattern
    @ResponseBody
    public String insert( @ModelAttribute Reply dto, HttpSession session, ModelAndView mav) {


        String userId = (String) session.getAttribute("nickname");
        System.out.println("userId 확인 ::: " + userId );
        dto.setReplyer(userId);
        System.out.println("아이디 확인 :::" + dto.getReplyer());
        //댓글 작성자 아이디
        //현재 접속중인 사용자 아이디
       /* String userid=(String)session.getAttribute("userid");*/
        /*String name = "작성자 빡";
        dto.setName(name);*/

        //댓글이 테이블에 저장됨
        boardService.create(dto);
        //jsp 페이지로 가거나 데이터를 리턴하지 않음
        return "success";
    }

   /* @GetMapping("/reply/delReply/{rno}")
    public String delReply(@PathVariable("rno") int rno){

        if(rno <= 0) {
            return "redirect:/board/view"
        }
        return ;
    }*/

    /**
     * <p>댓글 수정</p>
     * @param dto
     * @param session
     * @return
     */
    @PostMapping("/reply/update")
    @ResponseBody
    public String replyModify(@ModelAttribute Reply dto, HttpSession session){


        System.out.println("비밀글 체크 :::" + dto.getSecretReply());
        //댓글 작성자 아이디
        //현재 접속중인 사용자 아이디
        /* String userid=(String)session.getAttribute("userid");*/
        //댓글이 테이블에 저장됨
        boardService.modify(dto);
        return "success";
    }

    @PostMapping("/reply/replyDelete")
    @ResponseBody
    public String replyDelete(@ModelAttribute Reply dto, HttpSession session){
        System.out.println("삭제 도착 ::: " + dto);

        boardService.replyDelete(dto);

        return "success";
    }
}
