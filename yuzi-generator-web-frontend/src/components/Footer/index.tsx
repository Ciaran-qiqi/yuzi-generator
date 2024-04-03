import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMessage = '程序员群群';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'leetcode',
          title: '力扣链接',
          href: 'https://leetcode.cn/',
          blankTarget: true,
        },
        {
          key: 'Ant Design',
          title: '前端框架',
          href: 'https://ant-design.antgroup.com/index-cn',
          blankTarget: true,
        },
        {
          key: 'github',
          title: (
            <>
              <GithubOutlined /> 群群源码
            </>
          ),
          href: 'https://github.com/Ciaran-qiqi/yuzi-generator',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
