
## How to set up your Github page?

Each team will get access to its own repository, used to share all project-related information with your team mates but also to create and host you Software Engineering Blog.
This is a brief guideline, how to do this (NOTE: it refers to external web pages for more information on *how to do* particular steps).

Since your project already contains a predefined set of files for github pages (in the `/docs` folder), you only have to do the following steps

1. Navigate to your repository
2. Go to the settings of your repository.
3. In the part for enabling Github pages (just scroll down), select **master branch /docs folder** as your GitHub Pages publishing source. Omit the choice of a predefined Jekyll theme so far.
4. Congrats, you just created your Github page.
5. Note after enabling github pages, it also shows you the URL for your github page. Please put this in the top-level [README.de](./README.md) file of your repository.

For more information, see the [Github Help](https://help.github.com/categories/github-pages-basics/) (it provides a good entry point, also in understanding how Github pages works).

## Make you page Blog-Ready

So far, a page exists, but it looks rather plain and not suitable for hosting Blog posts. Here is how you get there very easily:

1. In the `_config.yml` file of your project (in the `\docs` folder), change the line `baseurl: isee2018` to `baseurl: <project-name>` where project name means the name of your github repository/project.

That's it! Of course, you are free to change the design as you like.
It may take some minutes until your web page appears in the newly created layout. The reason is that Github pages are backed by [Jekyll](http://jekyllrb.com/), a static site generator. This allows for an easy customization of your Github page, while abstracting from all the details. Most importantly, you can create your pages, blog posts etc. with **Markdown**, an easy-to-learn markup language, similar to those used in Wikis. Useful references for Markdown commands can be found [here](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#emphasis). Moreover, a [Markdown Cheat sheet](http://packetlife.net/media/library/16/Markdown.pdf) summarizes the most important ones.

Now, let's have a look whats inside the `/docs` folder:

`_config.yml` is the configuration file for Jekyll. Here, you can define global variables that can then be used, for instance, in HTML files or Markdown files. As an example, consider the defined variable `baseurl: isee2018`, which can be used in the form `{{ site.baseurl }}` in any other file that is used for your github page. As a result, the right side of the defined variable is inserted in the generated web page. For more information on variables see the [Jekyll Doc](https://jekyllrb.com/docs/variables/).

`_layouts` is the folder where you can define the layout of your web page using HTML. The file `default.html` defines the default layout of your web page. Additionally, you can define specific layouts for sub pages. For instance, the file `post.html` defines the layout for listing the Blog posts. It relies on the default layout, which is indicated by the following header:

```
---
layout: default
---
```
As a result, we keep up with the default layout, whereas the `post.html` just defines how the Blog posts are listed.
More general, you can reuse any of the Jekyll variable within this header.

`_posts` is the folder where your blog posts live in. For each Blog post you create a Markdown file (extension `.md`). Each of these file **must** follow a strict naming scheme, as it is required by Jekyll. Thus, it must follow the convention `YYYY-MM-DD-title-of-my-post.md`.
For instance, the file name of the first post of the template web page is `2018-04-03-ISEE-2018-initial-post.md`. Jekyll takes care that the original markdown file is converted to HTML. Hence, it is even possible to include HTML syntax.
