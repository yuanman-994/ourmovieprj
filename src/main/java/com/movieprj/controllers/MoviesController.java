package com.movieprj.controllers;

import com.movieprj.beans.*;
import com.movieprj.mapper.ScheduleMapper;
import com.movieprj.services.*;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping
public class MoviesController {
    @Autowired
    private MoviesServiceImp moviesService;

    @Autowired
    private CommentServiceImp commentService;

    @Autowired
    private ScheduleServiceImp scheduleService;

    @Autowired
    private HallServiceImp hallService;

    @Autowired
    private SeatServiceImp seatService;

    @Autowired
    private TicketServiceImp ticketService;

    @Autowired
    private TicketOrderServiceImp ticketOrderService;

    @Autowired
    private  MoviesTypesServiceImp moviesTypesService;

    @Autowired
    private ShowTypeServiceImp showTypeService;

    @RequestMapping("/movie_single")
    public  String toMoviePage(Integer movie_id ){
        return "redirect:/movie_single1?movie_id="+movie_id;
     }

    @RequestMapping("/movie_single1")
    public  String toMoviePage1(Integer movie_id){
        return "movie_single";
    }

    @RequestMapping("/movie_schedule")
    public  String toMovieSchedule(Integer movie_id ){
        return "redirect:/movie_schedule1?movie_id="+movie_id;
    }

    @RequestMapping("/movie_schedule1")
    public  String toMovieSchedule1(Integer movie_id ){
        return "movie_schedule";
    }

    @RequestMapping("/seats_select")
    public  String toSeatsSelect(Integer movie_schedule_id ){
        return "redirect:/seats_select1?movie_schedule_id="+movie_schedule_id;
    }

    @RequestMapping("/seats_select1")
    public  String toSeatsSelect1(Integer movie_schedule_id ){
        return "seats_select";
    }

    @RequestMapping("/schedule_cinema")
    @ResponseBody
    public Map<String,Object>findCinemaByMovieIdAndDate(@RequestBody Map<String,Object> params){
        Integer movie_id = Integer.parseInt(params.get("movie_id").toString());
        int day = Integer.parseInt(params.get("day").toString());
        String date_time;
        Date date=new Date();
        Date date1,date2;
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.add(Calendar.DAY_OF_MONTH,1);//利用Calendar 实现 Date日期+1天  
        date1 = c.getTime();
        c.add(Calendar.DAY_OF_MONTH,1);//利用Calendar 实现 Date日期+2天  
        date2 = c.getTime();
        if(day==0){//当前时间
            date_time=simpleDate.format(date);
        }else if (day==1){//明天
            date_time=simpleDate.format(date1);
        } else{//后天
            date_time=simpleDate.format(date2);
        }
        List<Cinema> cinemaList = scheduleService.findCinemaByMovieIdAndDate(movie_id,date_time);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cinemaList",cinemaList);
        return map;
    }


    @RequestMapping("/schedule_detail")
    @ResponseBody
    public Map<String,Object> findScheduleByMovieIdAndCinemaIdAndDate(@RequestBody Map<String,Object> params){
        Integer movie_id = Integer.parseInt(params.get("movie_id").toString());
        Integer cinema_id = Integer.parseInt(params.get("cinema_id").toString());
        int day = Integer.parseInt(params.get("day").toString());
        String date_time;
        Date date=new Date();
        Date date1,date2;
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.add(Calendar.DAY_OF_MONTH,1);//利用Calendar 实现 Date日期+1天  
        date1 = c.getTime();
        c.add(Calendar.DAY_OF_MONTH,1);//利用Calendar 实现 Date日期+2天  
        date2 = c.getTime();
        if(day==0){//当前时间
            date_time=simpleDate.format(date);
        }else if (day==1){//明天
            date_time=simpleDate.format(date1);
        } else{//后天
            date_time=simpleDate.format(date2);
        }
        List<MovieSchedule> scheduleList = scheduleService.findScheduleByMovieIdAndCinemaIdAndDate(cinema_id,movie_id,date_time);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("scheduleList",scheduleList);
        return map;
    }


    @RequestMapping("/moviesInfo")
    @ResponseBody
    public  Map<String,Object> ShowMoivesInfo(Integer movie_id){
        Movies movie = moviesService.selectMoviesById(movie_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("movie",movie);
        return map;
    }


    @RequestMapping("/moviesComment")
    @ResponseBody
    public  Map<String,Object> ShowMoivesComments(Integer movie_id){
        List<Comment> commentList = commentService.findCommentWithUserByMovieId(movie_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("commentList",commentList);
        return map;
    }
    @RequestMapping("/onshowMovies")
    @ResponseBody
    public Map<String,Object> ShowOnshowMoives(Integer onshow){
        List<Movies> moviesList = moviesService.selectMoviesByOnshow(onshow);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("moviesList",moviesList);
        return map;
    }



    @PostMapping("/addMComment")
    public String AddMComment(HttpServletRequest request, Model model) {
        Comment comment= new Comment();
        comment.setContent(request.getParameter("content"));
        if(request.getParameter("movie_id")!=null){
        Integer movie_id = Integer.valueOf(request.getParameter("movie_id"));
        comment.setMovie_id(movie_id);}
        if(request.getParameter("user_id")!=null){
        Integer user_id = Integer.valueOf(request.getParameter("user_id"));
        comment.setUser_id(user_id);}
        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setComment_time(simpleDate.format(date));
        commentService.insertComment(comment);
        return "redirect:/movie_single1?movie_id="+comment.getMovie_id();
    }


    @RequestMapping("/getSchedule")
    @ResponseBody
    public  Map<String,Object> getScheduleInf(Integer movie_schedule_id){
        MovieSchedule movieSchedule = scheduleService.findScheduleById(movie_schedule_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("movieSchedule",movieSchedule);
        return map;
    }

    @RequestMapping("/getHall")
    @ResponseBody
    public  Map<String,Object> getHall(Integer hall_id){
        Hall hall = hallService.findHallById(hall_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hall",hall);
        return map;
    }

    @RequestMapping("/getSoldSeat")
    @ResponseBody
    public Map<String,Object> findSoldSeatByScheduleId(Integer movie_schedule_id){
        List<Seat> sold_seat = seatService.findSoldSeatByScheduleId(movie_schedule_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sold_seat",sold_seat);
        return map;
    }

    @RequestMapping("/addTicket")
    @ResponseBody
    public Map<String,Object>addTicket(@RequestBody Map<String,Object> params){
        Integer movie_schedule_id = Integer.parseInt(params.get("movie_schedule_id").toString());
        Integer hall_id = Integer.parseInt(params.get("hall_id").toString());
        String seats = params.get("seats").toString();
        String[] s = seats.replaceAll(" ","").replaceAll("\\[","").replaceAll("\\]","").split(",");
        String[] a;
        Ticket ticket = new Ticket();
        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");
        Integer order_id ;
        order_id = Integer.parseInt(simpleDate.format(date))*100+movie_schedule_id;
        ticket.setMovie_schedule_id(movie_schedule_id);
        ticket.setOrder_id(order_id);
        int[] seat_id={0,0,0,0,0};
        if(s.length<=5){
        for(int i =0; i<s.length;i++){
          a=s[i].split("_");
          seat_id[i]= hall_id*1000+(Integer.parseInt(a[0])-1)*10+Integer.parseInt(a[1]);
          ticket.setSeat_id(seat_id[i]);
          ticket.setStatus("1");
          ticketService.addTicket(ticket);
        }}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seats",seat_id);
        return map;
    }

    @RequestMapping("/addTicketOrder")
    @ResponseBody
    public Map<String,Object>addTicketOrder(@RequestBody Map<String,Object> params){
        Integer movie_schedule_id = Integer.parseInt(params.get("movie_schedule_id").toString());
        Integer user_id = Integer.parseInt(params.get("user_id").toString());
        Float total = Float.parseFloat(params.get("total").toString());
        TicketOrder ticketOrder = new TicketOrder();
        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDate1 = new SimpleDateFormat("yyyyMMdd");
        Integer order_id = Integer.parseInt(simpleDate1.format(date))*100+movie_schedule_id;
        ticketOrder.setMovie_schedule_id(movie_schedule_id);
        ticketOrder.setUser_id(user_id);
        ticketOrder.setPrice(total);
        ticketOrder.setPay_way("alipay");
        ticketOrder.setTime(simpleDate.format(date));
        ticketOrder.setOrder_id(order_id);
        ticketOrderService.addTicketOrder(ticketOrder);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order",ticketOrder);
        return map;
    }


    @RequestMapping("/admin_movies")
    public  String admin_movies(){
        return "admin_movies";
    }

    @RequestMapping("/getAllMovies")
    @ResponseBody
    public Map<String,Object> getAllMovies() {
        List<Movies> moviesList = moviesService.findAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("moviesList",moviesList);
        return map;
    }

    @RequestMapping("/getAllWithTypes")
    @ResponseBody
    public Map<String,Object> selectMoviesWithTypes(){
        List<Movies> moviesList = moviesService.findAllWithTypes();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("moviesList",moviesList);
        return map;
    }

    @RequestMapping("/findMoviesWithType")
    @ResponseBody
    public Map<String,Object> findMovies(Integer movie_id) {
        Movies movies = moviesService.selectMoviesWithTypesById(movie_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("movies",movies);
        return map;
    }

    @PostMapping("/deleteMovies")
    @ResponseBody
    public Map<String,Integer> deleteMovies(@RequestBody Map<String,List> movies_ids) {
        int check;
        check = moviesService.deleteMovies(movies_ids);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("check",check);
        return map;
    }

    @PostMapping("/updateMovies")
    public String updateMovies(HttpServletRequest request,@RequestParam("cover1") List<MultipartFile> uploadfile) {
        Movies movies = new Movies();
        Integer movie_id =null;
        String movie_name = null;
        if(request.getParameter("movie_name")!=null){
            movie_name = request.getParameter("movie_name");
            movies.setMovie_name(movie_name);}
        if(request.getParameter("movie_id")!=null){
            movie_id = Integer.valueOf(request.getParameter("movie_id"));
            movies.setMovie_id(movie_id);
        }
        if(request.getParameter("director")!=null){
            String director = request.getParameter("director");
            movies.setDirector(director);}
        if(request.getParameter("main_actor")!=null){
            String main_actor = request.getParameter("main_actor");
            movies.setMain_actor(main_actor);}
        if(request.getParameter("intro")!=null){
            String intro = request.getParameter("intro");
            movies.setIntro(intro);}
        if(request.getParameter("rank")!=null){
            Integer rank = Integer.valueOf(request.getParameter("rank"));
            movies.setRank(rank);}
        if(request.getParameter("release_date")!=null){
            String release_date = request.getParameter("release_date");
            movies.setRelease_date(release_date);}
        if(request.getParameter("onshow")!=null){
            Integer onshow = Integer.valueOf(request.getParameter("onshow"));
            movies.setOnshow(onshow);}

        if (!uploadfile.isEmpty() && uploadfile.size()>0) {
            //循环输出上传的文件  MultipartFile 支持传输多个文件
            for (MultipartFile file : uploadfile) {

                String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
                // 获取上传文件的原始名称
                String originalFilename = file.getOriginalFilename();
                // 设置上传文件的保存地址目录
                String urlPath =
                        "images/";
                String savePath = staticPath + "/" + urlPath;
                String visitPath = urlPath;
                File filePath = new File(savePath);
                // 如果保存文件的地址不存在，就先创建目录
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                // 使用UUID重新命名上传的文件名称(上传人_uuid_原始文件名称)
                String newFilename = movie_name + "_" + UUID.randomUUID() +
                        "_" + originalFilename;
                try {
                    // 使用MultipartFile接口的方法完成文件上传到指定位置
                    if(!file.isEmpty()){
                        file.transferTo(new File(savePath + newFilename));
                        movies.setCover(visitPath+newFilename);
                    }
                    else {
                        Movies movies1= moviesService.selectMoviesById(movie_id);
                        movies.setCover(movies1.getCover());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return "error";
                }

            }
        } else {

        }

        for(int i =0;i<3;i++){
            if(request.getParameter("selectType"+i)!=null && !request.getParameter("selectType"+i).equals("none")){
                MoviesTypes moviesTypes = new MoviesTypes();
                String type = request.getParameter("selectType"+i);
                Integer type_id =  Integer.valueOf(request.getParameter("type_id"+i));
                moviesTypes.setType_name(type);
                moviesTypes.setMovie_id(movie_id);
                moviesTypes.setMovie_type_id(type_id);
                moviesTypesService.updateMoviesTypes1(moviesTypes);
            }
        }

        Map<String, Object> moviesMap = new HashMap<String,Object>();
        moviesMap.put("moviesMap",movies);
        moviesService.updateMovies(moviesMap);

        return "redirect:/admin_movies";
    }

    @PostMapping("/addMovies")
    public String addMovies(HttpServletRequest request,@RequestParam("cover") List<MultipartFile> uploadfile) {
        Movies movies = new Movies();
        Integer movie_id =null;
        String movie_name = null;
        if(request.getParameter("movie_name")!=null){
            movie_name = request.getParameter("movie_name");
            movies.setMovie_name(movie_name);}
        if (!uploadfile.isEmpty() && uploadfile.size() > 0) {
            //循环输出上传的文件  MultipartFile 支持传输多个文件
            for (MultipartFile file : uploadfile) {

                String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
                // 获取上传文件的原始名称
                String originalFilename = file.getOriginalFilename();
                // 设置上传文件的保存地址目录
                String urlPath =
                        "images/";
                String savePath = staticPath + "/" + urlPath;
                String visitPath = urlPath;
                File filePath = new File(savePath);
                // 如果保存文件的地址不存在，就先创建目录
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                // 使用UUID重新命名上传的文件名称(上传人_uuid_原始文件名称)
                String newFilename = movie_name + "_" + UUID.randomUUID() +
                        "_" + originalFilename;
                try {
                    // 使用MultipartFile接口的方法完成文件上传到指定位置
                    file.transferTo(new File(savePath + newFilename));
                } catch (Exception e) {
                    e.printStackTrace();
                    return "error";
                }
                 movies.setCover(visitPath+newFilename);
            }
        } else {
            return "error";
        }
        if(request.getParameter("movie_id")!=null){
            movie_id = Integer.valueOf(request.getParameter("movie_id"));
            movies.setMovie_id(movie_id);
        }
        if(request.getParameter("director")!=null){
            String director = request.getParameter("director");
            movies.setDirector(director);}
        if(request.getParameter("main_actor")!=null){
            String main_actor = request.getParameter("main_actor");
            movies.setMain_actor(main_actor);}
        if(request.getParameter("intro")!=null){
            String intro = request.getParameter("intro");
            movies.setIntro(intro);}
        if(request.getParameter("rank")!=null){
            Integer rank = Integer.valueOf(request.getParameter("rank"));
            movies.setRank(rank);}
        if(request.getParameter("release_date")!=null){
            String release_date = request.getParameter("release_date");
            movies.setRelease_date(release_date);}
        if(request.getParameter("onshow")!=null){
            Integer onshow = Integer.valueOf(request.getParameter("onshow"));
            movies.setOnshow(onshow);}
        for(int i =0;i<3;i++){
            if(request.getParameter("selectType"+i)!=null && !request.getParameter("selectType"+i).equals("none")){
                MoviesTypes moviesTypes = new MoviesTypes();
                String type = request.getParameter("selectType"+i);
                moviesTypes.setType_name(type);
                moviesTypes.setMovie_id(movie_id);
                moviesTypesService.addMoviesType1(moviesTypes);
            }
        }

        Map<String, Object> moviesMap = new HashMap<String,Object>();
        moviesMap.put("moviesMap",movies);
        moviesService.addMovies(moviesMap);
        return "redirect:/admin_movies";
    }

    @RequestMapping("/admin_moviestypes")
    public  String admin_moviestypes(){
        return "admin_moviestypes";
    }

    @RequestMapping("/getTypes")
    @ResponseBody
    public Map<String,Object> getTypes(){
        List<String> moviesTypesList = moviesTypesService.findTypesName();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("moviesTypesList",moviesTypesList);
        return map;
    }


    @PostMapping("/deleteTypes")
    @ResponseBody
    public Map<String,Integer> deleteTypes(@RequestBody Map<String,List> types_names) {
        int check;
        check = moviesTypesService.deleteMoviesTypes(types_names);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("check",check);
        return map;
    }

    @PostMapping("/updateTypes")
    public String updateTypes(HttpServletRequest request) {
        String new_type_name = request.getParameter("new_type_name").toString();
        String old_type_name = request.getParameter("old_type_name").toString();
        moviesTypesService.updateMoviesTypes(new_type_name,old_type_name);
        return "redirect:/admin_moviestypes";
    }

    @PostMapping("/addTypes")
    public String addTypes(HttpServletRequest request) {
        String type_name = request.getParameter("type_name").toString();
        MoviesTypes moviesTypes = new MoviesTypes();
        moviesTypes.setType_name(type_name);
        moviesTypes.setMovie_id(null);
        moviesTypesService.addMoviesType(moviesTypes);
        return "redirect:/admin_moviestypes";
    }

    @RequestMapping("/admin_showtype")
    public  String admin_showtype(){
        return "admin_showtype";
    }

    @RequestMapping("/getShowType")
    @ResponseBody
    public Map<String,Object> getShowType(){
        List<ShowType> showTypesList = showTypeService.findAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("showTypesList",showTypesList);
        return map;
    }

    @PostMapping("/deleteShowType")
    @ResponseBody
    public Map<String,Integer> deleteShowType(@RequestBody Map<String,List> show_types) {
        int check;
        check = showTypeService.deleteShowType(show_types);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("check",check);
        return map;
    }

    @PostMapping("/updateShowType")
    public String updateShowType(HttpServletRequest request) {
        String new_show_type = request.getParameter("new_show_type").toString();
        String old_show_type = request.getParameter("old_show_type").toString();
        showTypeService.updateShowType(new_show_type,old_show_type);
        return "redirect:/admin_showtype";
    }

    @PostMapping("/addShowType")
    public String addShowType(HttpServletRequest request) {
        String show_type = request.getParameter("show_type").toString();
        ShowType showType = new ShowType();
        showType.setShow_type(show_type);
        showTypeService.addShowType(showType);
        return "redirect:/admin_showtype";
    }

    @RequestMapping("/admin_moviecomment")
    public  String admin_moviecomment(){
        return "admin_moviecomment";
    }

    @RequestMapping("/findMovieComment")
    @ResponseBody
    public  Map<String,Object> findComment(Integer movie_id,Integer user_id){
        Comment comment = commentService.findCommentWithUserByPk(movie_id,user_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("comment",comment);
        return map;
    }

    @RequestMapping("/findAllMovieComment")
    @ResponseBody
    public  Map<String,Object> findAllComment(){
        List<Comment> commentList = commentService.findAllCommentWithUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("commentList",commentList);
        return map;
    }

    @PostMapping("/deleteMovieComment")
    @ResponseBody
    public Map<String,Integer> deleteComment(@RequestBody Map<String,List> mu_ids) {
        int check;
        check = commentService.deleteComment(mu_ids);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("check",check);
        return map;
    }

    @PostMapping("/addMovieComment")
    public String addComment(HttpServletRequest request) {
        Integer movie_id,user_id;
        String content,comment_time;
        Comment comment = new Comment();
        if(request.getParameter("movie_id")!=null){
            movie_id = Integer.valueOf(request.getParameter("movie_id").toString());
            comment.setMovie_id(movie_id);
        }
        if(request.getParameter("user_id")!=null){
            user_id = Integer.valueOf(request.getParameter("user_id").toString());
            comment.setUser_id(user_id);
        }
        if(request.getParameter("content")!=null){
            content = request.getParameter("content").toString();
            comment.setContent(content);
        }
        if(request.getParameter("comment_time")!=null){
            comment_time = request.getParameter("comment_time").toString();
            comment.setComment_time(comment_time);
        }
        commentService.insertComment(comment);
        return "redirect:/admin_moviecomment";
    }


}

