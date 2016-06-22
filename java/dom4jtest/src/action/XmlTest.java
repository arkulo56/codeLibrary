package action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import model.Book;

public class XmlTest {
	private Book book;
	public XmlTest()
	{
		book = new Book();
		book.setLanguage("chinese");
		book.setName("托马斯和他的伙伴们");
		book.setPrice(20.00);
		book.setYear("2001");
		book.setAuthor("arkulo");
	}
	//读xml
	private void readXml() throws DocumentException
	{
		//初始化SAXReader
		SAXReader hd = new SAXReader();
		//获取文件
		Document dc = hd.read("src/res/newbook.xml");
		//跟节点
		Element eme = dc.getRootElement();
		//遍历根节点下的所有节点
		Iterator it = eme.elementIterator();
		while(it.hasNext())
		{
			Element book = (Element)it.next();
			//遍历一本书的属性
			List<Attribute> at = book.attributes();
			int account = at.size();
			for(int i=0;i<account;i++)
			{
				Attribute oneAt = at.get(i);
				System.out.println("属性名称:"+oneAt.getName()+" / 属性值:"+oneAt.getValue());
			}
			//遍历子节点
			Iterator child = book.elementIterator();
			while(child.hasNext())
			{
				Element childEle = (Element)child.next();
				System.out.println("书的属性："+childEle.getName()+" / 值："+childEle.getStringValue());
			}
			System.out.println("----------");
		}
	}
	
	//写xml
	private void createXml()
	{
		//创建document对象
		Document document = DocumentHelper.createDocument();
		//创建rss元素
		//Element rss = document.addElement("rss");
		//rss.addAttribute("version", "2.0");
		//创建其他元素
		Element bookstore = document.addElement("bookstore");
		for(int i=0;i<10000;i++)
		{
			//创建book元素
			Element books = bookstore.addElement("book");
			books.addAttribute("id", Integer.toString(i));
			Element name = books.addElement("name");
			name.setText(book.getName());
			Element auther = books.addElement("auther");
			auther.setText(book.getAuthor());
			Element year = books.addElement("year");
			year.setText(book.getYear());
			Element language = books.addElement("language");
			language.setText(book.getLanguage());
			Element price = books.addElement("price");
			price.setText(Double.toString(book.getPrice()));
		}
		//创建文件
		File file = new File("src/res/newbook.xml");
		//初始化XMLWriter对象
		try
		{
			//设置xml的格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter xw = new XMLWriter(new FileOutputStream(file),format);
			xw.setEscapeText(false);
			xw.write(document);
			xw.close();
		}catch(UnsupportedEncodingException e)
		{			
		}catch(FileNotFoundException e)
		{			
		}catch(IOException e)
		{}
	}
	
	public static void main(String[] args) {
		XmlTest xml = new XmlTest();
		try
		{
			xml.readXml();
			//xml.createXml();
		}catch(Exception e)
		{
			
		}
	}
}
